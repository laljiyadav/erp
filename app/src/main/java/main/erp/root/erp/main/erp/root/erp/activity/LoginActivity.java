package main.erp.root.erp.main.erp.root.erp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.CheckInternetUtil;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.execute.LoginExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;


/**
 * Created by root on 11/2/15.
 */
public class LoginActivity extends Activity implements LoginDone {


    EditText edtEmail,edtPassword;
    Button btnSignIn;
    //private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    String value;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
      //  value=getIntent().getStringExtra("value");
       // title=(TextView)findViewById(R.id.txtTitle);
      //  title.setText(value);
//        Log.d("Login Activity", value);

        edtEmail=(EditText)findViewById(R.id.email);
        edtPassword=(EditText)findViewById(R.id.password);
        btnSignIn=(Button)findViewById(R.id.email_sign_in_button);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!CheckInternetUtil.isInternetAvailable(LoginActivity.this)){
                    Toast.makeText(LoginActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }else {
                    String validate=Validate();
                    if(validate!=null) {
                        Toast.makeText(LoginActivity.this,validate,Toast.LENGTH_SHORT).show();
                    }else{
                        new LoginExecute(LoginActivity.this, MyConfig.Serverurl + "api_Student/login.php?eid=" + edtEmail.getText().toString() + "&pass=" + edtPassword.getText().toString()).execute();

//                        if(value.equalsIgnoreCase("Student")){
//
//
//                        }else if(value.equals("Teacher")){
//
//                            new LoginExecute(LoginActivity.this,MyConfig.Serverurl + "api_Teacher/login.php?eid=" + edtEmail.getText().toString() + "&pass=" + edtPassword.getText().toString()).execute();
//
//                        } else if(value.equals("Parent")){
//
//                            new LoginExecute(LoginActivity.this,MyConfig.Serverurl + "api_Student/login.php?eid=" + edtEmail.getText().toString() + "&pass=" + edtPassword.getText().toString()).execute();
//
//                        }



                    }
                }
            }
        });



    }
    private String Validate() {

        if(edtEmail.getText().toString()==null || edtEmail.getText().length()==0){
            return "Please Enter the Login Id.";
        }else if(edtPassword.getText().toString()==null || edtPassword.getText().length()==0){
            return "Please Enter the Password.";
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void getResult(String s) {
        try {
            //JSONObject jo = new JSONObject(s.substring(1, s.length() - 1));
            JSONObject jo = new JSONObject(s);
            String cid = jo.getString("cid");
            String std_id = jo.getString("Std_Pkey");
            String classid = jo.getString("Cls_id");
            String sec_id = jo.getString("sec_id");
            String className = jo.getString("Class");
            String first_name = jo.getString("Fname");
            String last_name = jo.getString("Lname");
            String middle_name = jo.getString("Mname");
            String section = jo.getString("Section");
            String status = jo.getString("status");

            if (cid != null) {

                saveSharedId(cid, std_id,classid,sec_id, className, first_name, last_name, middle_name, section, status,jo.getString("Photo"));
                Intent in = new Intent(LoginActivity.this, StudentActivity.class);
                in.putExtra("value", "Student");
                startActivity(in);
                finish();
            }


        } catch (Exception e) {


//        }
//        if(value.equalsIgnoreCase("Student")) {
//            //Log.d("result", "" + s.substring(1, s.length() - 1));
//
//            try {
//                //JSONObject jo = new JSONObject(s.substring(1, s.length() - 1));
//                JSONObject jo = new JSONObject(s);
//                String cid = jo.getString("cid");
//                String std_id = jo.getString("Std_Pkey");
//                String classid = jo.getString("Cls_id");
//                String sec_id = jo.getString("sec_id");
//                String className = jo.getString("Class");
//                String first_name = jo.getString("Fname");
//                String last_name = jo.getString("Lname");
//                String middle_name = jo.getString("Mname");
//                String section = jo.getString("Section");
//                String status = jo.getString("status");
//
//                if (cid != null) {
//
//                    saveSharedId(cid, std_id,classid,sec_id, className, first_name, last_name, middle_name, section, status,jo.getString("Photo"));
//                    Intent in = new Intent(LoginActivity.this, StudentActivity.class);
//                    in.putExtra("value", "Student");
//                    startActivity(in);
//                    finish();
//                }
//
//
//            } catch (Exception e) {
//
//
//            }
//        }else  if(value.equals("Teacher")){
//            Log.d("result", "Teacher"+s);
//            try {
//                JSONObject jo = new JSONObject(s.substring(1, s.length() - 1));
//                String status=jo.getString("status");
//              if(status.equals("1")) {
//
//              String cid = jo.getString("cid");
//              String empId = jo.getString("Emp_Pkey");
//              String fName = jo.getString("Fname");
//              String mName = jo.getString("Mname");
//              String lName = jo.getString("Lname");
//
//              saveSharedIdTeacher(cid,empId,fName,mName,lName);
//
//              Intent in = new Intent(LoginActivity.this, TeacherActivity.class);
//              in.putExtra("value", "StudenT login");
//              startActivity(in);
//              finish();
//
//          }else{
//              Toast.makeText(LoginActivity.this,"Invalid User",Toast.LENGTH_SHORT).show();
//          }
//
//            }catch (Exception e){
//
//            }
//        } else if(value.equals("Parent")){
//            Log.d("result", "Parent"+s);
//            Log.d("result", "" + s.substring(1, s.length() - 1));
//            try {
//                JSONObject jo = new JSONObject(s.substring(1, s.length() - 1));
//                String class_id = jo.getString("cid");
//                String std_id = jo.getString("Std_Pkey");
//                String classid = jo.getString("Cls_id");
//                String sec_id = jo.getString("sec_id");
//                String classNmae = jo.getString("Class");
//                String first_name = jo.getString("Fname");
//                String last_name = jo.getString("Lname");
//                String middle_name = jo.getString("Mname");
//                String section = jo.getString("Section");
//                String status = jo.getString("status");
//
//
//                if (class_id != null) {
//                    saveSharedId(class_id, std_id,classid,sec_id, classNmae, first_name, last_name, middle_name, section, status,jo.getString("Photo"));
//                    Intent in = new Intent(LoginActivity.this, StudentActivity.class);
//                    in.putExtra("value", "Student");
//                    startActivity(in);
//                    finish();
//                }
//
//
//            } catch (Exception e) {
//
//
//            }
//
//
//
     }

    }

    private void saveSharedIdTeacher(String cid, String empId, String fName, String mName, String lName) {

        SharedPreferences myPrefs=getSharedPreferences("teacher", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("cid", cid);
        editor.putString("empId", empId);
        editor.putString("first_name", fName);
        editor.putString("middle_name", mName);
        editor.putString("last_name", lName);
       // editor.putBoolean("remember", true);


        editor.commit();

        SharedPreferences myPrefs1=getSharedPreferences("main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = myPrefs1.edit();
        editor1.putString("value", "teacher");
        editor1.putBoolean("remember", true);
        editor1.commit();


    }

    private void saveSharedId(String cid, String std_id,String cls_id,String sec_id, String classname, String first_name, String last_name, String middle_name, String section, String status,String imgurl) {

        SharedPreferences myPrefs=getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("cid", cid);
        editor.putString("std_id", std_id);
        editor.putString("clsid", cls_id);
        editor.putString("secid", sec_id);
        editor.putString("classname", classname);
        editor.putString("first_name", first_name);
        editor.putString("middle_name", middle_name);
        editor.putString("last_name", last_name);
        editor.putString("section", section);
        editor.putString("status", status);
        editor.putString("value", "student");
        editor.putString("imageurl", imgurl);
        editor.commit();

        SharedPreferences myPrefs1=getSharedPreferences("main", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = myPrefs1.edit();
        editor1.putString("value", "student");
        editor1.putBoolean("remember", true);
        editor1.commit();

    }
}
