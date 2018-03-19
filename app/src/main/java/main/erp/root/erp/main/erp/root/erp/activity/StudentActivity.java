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
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.CalenderFragment;

import main.erp.root.erp.main.erp.root.erp.StudentFragment.CalenderOptionFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.Circular_Fragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.CommunicationFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.FeeSubmitOption;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.FeesDEtailAttendance;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.FragmentDrawer;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.HomeFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.HomeWorkFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.LibraryFagment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.MonthlyAttendance;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.ProfileFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.ResultFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.RmarkFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.TransportFragment;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.execute.studentExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

/**
 * Created by root on 11/26/15.
 */
public class StudentActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener, LoginDone {


    private static String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences mPrefs;
    String cid, clsId, std_id, sacId,className,first_name,last_name, middle_name,date="02-2017";
    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;
    public RefresList refresh;
    public ImageView imgstudent;
    GridView g;
    TextView txtcid,txtname,txtcls;
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    int current_month=calendar.get(Calendar.MONTH);
    int next_month=calendar.get(Calendar.MONTH)+1;
    int pre_month= calendar.get(Calendar.MONTH)-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        txtcid= (TextView) findViewById(R.id.std_id);
        txtcls= (TextView) findViewById(R.id.std_cls);
        txtname= (TextView) findViewById(R.id.std_name);
        mPrefs = getSharedPreferences("login", Context.MODE_PRIVATE);
        cid = mPrefs.getString("cid", "");
        clsId = mPrefs.getString("clsid", "");
        std_id = mPrefs.getString("std_id", "");
        sacId = mPrefs.getString("secid", "");
        className=mPrefs.getString("classname","");
        first_name=mPrefs.getString("first_name","");
        middle_name=mPrefs.getString("middle_name","");
        last_name=mPrefs.getString("last_name","");

        txtcid.setText("ID : "+""+cid);
        txtcls.setText("Class : "+""+className);
        txtname.setText(first_name+" "+middle_name+" "+last_name);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        drawerFragment = (FragmentDrawer)getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);
        imgstudent = (ImageView) findViewById(R.id.imgstudent);
        SharedPreferences myPrefs = getSharedPreferences("login", Context.MODE_PRIVATE);
        if (!myPrefs.getString("imageurl", "").equals(""))
            Picasso.with(this).load(myPrefs.getString("imageurl", ""))
                    .error(R.drawable.ic_profile)
                    .placeholder(R.drawable.ic_profile).skipMemoryCache()
                    .into(imgstudent);

        displayView(0);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                fragment = new ProfileFragment();
                new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getProfile.php?card_id=" + cid).execute();
                title = getString(R.string.title_profile);
                break;

            case 2:
                fragment = new MonthlyAttendance();
                new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/monthlyAttendance.php?cid=" + cid).execute();
                title = getString(R.string.title_monthly);
                break;
            case 3:
                fragment = new Circular_Fragment();
                new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getMessage.php?Std_Pkey=" + std_id).execute();
                title = "Circular";
                break;
            case 4:
                fragment = new HomeWorkFragment();
                new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getAssignment.php?cls_id=" + clsId + "&sec_id=" + sacId).execute();
                title = "HomeWork";
                break;

            case 5:
//                Calendar calendar = Calendar.getInstance();
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                //2016-07-21
                //sdf.format(calendar.getTime());
//                fragment = new CalenderFragment();
//                new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getEvents.php?cls_id=" + clsId + "&sec_id=" + sacId + "&date=" +"2016-07-21").execute();
                fragment = new CalenderOptionFragment();
                title = "Calendar";
                break;
            case 6:
                fragment = new RmarkFragment();
                new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getRemarks.php?card_id=" + cid).execute();
                title = "Remark";
                break;

            case 7:
                try {
                    fragment = new ResultFragment();
                    new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getResult.php?Std_Pkey=" + std_id + "&cls_id=" + clsId).execute();
                } catch (Exception e) {
                    Log.d("exception", e.getMessage());
                }
                title = getString(R.string.title_fees);
                break;

            case 8:
                try {
                    fragment = new FeeSubmitOption();
                  //  new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getFee.php?cid=" + cid + "&cls_id=" + clsId).execute();
                } catch (Exception e) {
                    Log.d("exception", e.getMessage());
                }
                title = getString(R.string.title_fees);
                break;
            case 9:
                fragment = new TransportFragment();
                new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getTransportation.php?cid=" + cid).execute();
                title = getString(R.string.title_transport);
                break;
            case 10:
                fragment = new CommunicationFragment();
                title = "Communication";
                break;
            case 11:
                fragment = new LibraryFagment();
                new studentExecute(StudentActivity.this, MyConfig.Serverurl + "api_Student/getLiebrarybook.php?cid=" + cid).execute();
                title = "Library";
                break;
            case 12:
                SharedPreferences myPrefs = getSharedPreferences("main", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putBoolean("remember", false);
                editor.putString("value", "");
                editor.commit();
                Intent inn = new Intent(StudentActivity.this, MainActivity.class);
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
            fragmentTransaction.addToBackStack(title);
            fragmentTransaction.commit();
            getSupportActionBar().setTitle(title);
        }
    }

    @Override
    public void getResult(String s) {
        //Log.d("k1", s);
        refresh.getRefreshDone(s);


    }
}
