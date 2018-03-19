package main.erp.root.erp.main.erp.root.erp.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

import main.erp.root.erp.R;

public class MainActivity extends Activity {
    Button btn_login;
    ImageView student,teacher,parent;
    boolean  rememberMe;
    String value;
    private SharedPreferences mPrefs,mPrefs1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //    mPrefs   = getSharedPreferences("login", Context.MODE_PRIVATE);


        mPrefs1= getSharedPreferences("main", Context.MODE_PRIVATE);
        rememberMe= mPrefs1.getBoolean("remember", false);
        value=mPrefs1.getString("value", "");

        Log.d("main value",Boolean.valueOf(rememberMe).toString()+"  "+value);
        if(rememberMe){
            if(value.equals("student")){
                Intent in=new Intent(MainActivity.this,StudentActivity.class);
                startActivity(in);
                finish();
            }else if(value.equals("teacher")){
                Intent in=new Intent(MainActivity.this,TeacherActivity.class);
                startActivity(in);
                finish();

            }else if(value.equals("")){
                Intent in=new Intent(MainActivity.this,StudentActivity.class);
                startActivity(in);
                finish();
            }
        }else {
        }

        student=(ImageView)findViewById(R.id.imgStudent);

        btn_login= (Button) findViewById(R.id.btn_std_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(in);
            }
        });




//        //teacher=(ImageView)findViewById(R.id.imgTeacher);
//        parent=(ImageView)findViewById(R.id.imgParent);
//        student.setOnClickListener(this);
////        teacher.setOnClickListener(this);
//        parent.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

//    @Override
//    public void onClick(View v) {
//        if(v==student){
//            Intent in=new Intent(MainActivity.this,LoginActivity.class);
//            in.putExtra("value","Student");
//            startActivity(in);
//            finish();
//        }
////        else if(v==teacher){
////            Intent in=new Intent(MainActivity.this,LoginActivity.class);
////            in.putExtra("value","Teacher");
////            startActivity(in);
////            finish();
// //       }
//        else if(v==parent){
//            Intent in=new Intent(MainActivity.this,LoginActivity.class);
//            in.putExtra("value","Parent");
//            startActivity(in);
//            finish();
//        }
//    }
}
