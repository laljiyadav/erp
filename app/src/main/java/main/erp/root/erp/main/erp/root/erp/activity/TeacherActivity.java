package main.erp.root.erp.main.erp.root.erp.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.TeacherFragment.InfStudentFragment;
import main.erp.root.erp.main.erp.root.erp.TeacherFragment.TeacherDrawer;
import main.erp.root.erp.main.erp.root.erp.TeacherFragment.TeacherHome;
import main.erp.root.erp.main.erp.root.erp.TeacherFragment.TeacherProfile;
import main.erp.root.erp.main.erp.root.erp.Utils.CLassItem;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.Utils.SectionItem;
import main.erp.root.erp.main.erp.root.erp.execute.SectionExecute;
import main.erp.root.erp.main.erp.root.erp.execute.TeacherExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.InfoDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;
import main.erp.root.erp.main.erp.root.erp.interfaces.SectionDOnee;
import main.erp.root.erp.main.erp.root.erp.interfaces.TeacherDone;


/**
 * Created by root on 11/26/15.
 */
public class TeacherActivity extends AppCompatActivity implements TeacherDrawer.FragmentDrawerListener,TeacherDone,SectionDOnee {


    private static String TAG = MainActivity.class.getSimpleName();
    ArrayList<CLassItem> listItem=new ArrayList<>();
    ArrayList<SectionItem> sectionList=new ArrayList<>();
    private Toolbar mToolbar;
    private TeacherDrawer drawerFragment;
    public  InfoDone inf;
    public RefresList refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);


        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (TeacherDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);


        displayView(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment = new TeacherHome();
                title = "Home";
                break;
            case 1:
                fragment = new TeacherProfile();
                title = "Teacher Profile";
                break;

            case 2:

                Bundle bundle = new Bundle();
                String myMessage = "info";
                bundle.putString("message", myMessage );
                fragment = new InfStudentFragment();
                fragment.setArguments(bundle);
                new TeacherExecute(TeacherActivity.this, MyConfig.Serverurl +"api_Teacher/getClass.php").execute();
                title = "Student Detail";
                break;
            case 3:
                Bundle bundle1 = new Bundle();
                String myMessage1 = "transport";
                bundle1.putString("message", myMessage1 );
                fragment = new InfStudentFragment();
                fragment.setArguments(bundle1);
                new TeacherExecute(TeacherActivity.this,MyConfig.Serverurl + "api_Teacher/getClass.php").execute();
                title = "Student Transport Information";

                break;
            case 4:
                Bundle bundle2 = new Bundle();

                bundle2.putString("message", "fee" );
                fragment = new InfStudentFragment();
                fragment.setArguments(bundle2);
                new TeacherExecute(TeacherActivity.this,MyConfig.Serverurl + "api_Teacher/getClass.php").execute();
                title = "Student Fees";
                break;
            case 5:
                SharedPreferences myPrefs=getSharedPreferences("main", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putBoolean("remember", false);
                editor.putString("value", "");
                editor.commit();
                Intent inn=new Intent(TeacherActivity.this,MainActivity.class);
                startActivity(inn);
                finish();
                break;

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }
    @Override
    public void getSectionData(String s) {

    }

    @Override
    public void getResult(String s) {
try {
    listItem.clear();
    Log.d("class", s);

    JSONArray jArray = new JSONArray(s);
    JSONObject jObject = null;

    for (int i = 0; i < jArray.length(); i++) {
        jObject = jArray.getJSONObject(i);
        String id = jObject.getString("cid");
        String className = jObject.getString("cname");
        listItem.add(new CLassItem(id, className));
    }
    inf.Done(listItem);

}catch (Exception e){

}

    }

    @Override
    public void getSectionResult(String s) {
        sectionList.clear();
        Log.d("section value",s);

        try {
            Log.d("class", s);

            JSONArray jArray = new JSONArray(s);
            JSONObject jObject = null;

            for (int i = 0; i < jArray.length(); i++) {
                jObject = jArray.getJSONObject(i);
                String sid1 = jObject.getString("sid");
                String sectionName1 = jObject.getString("sname");
                sectionList.add(new SectionItem(sid1, sectionName1));
            }
            inf.SEcDone(sectionList);

        }catch (Exception e){

        }

    }

    @Override
    public void sendSEctionData(String idValue) {

        Log.d("value",idValue);
        new SectionExecute(TeacherActivity.this,"http://webfastfix.com/sipl/api_Teacher/getSection.php?id="+idValue).execute();
    }
}


