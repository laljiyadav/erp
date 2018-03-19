package main.erp.root.erp.main.erp.root.erp.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.Utils.TeacherFeeItem;
import main.erp.root.erp.main.erp.root.erp.Utils.TeacherInfoItem;
import main.erp.root.erp.main.erp.root.erp.Utils.TeacherTransportItem;
import main.erp.root.erp.main.erp.root.erp.adapter.StudenTRansportAdapter;
import main.erp.root.erp.main.erp.root.erp.adapter.StudentFeeAdapter;
import main.erp.root.erp.main.erp.root.erp.adapter.StudentInfoAdapter;
import main.erp.root.erp.main.erp.root.erp.execute.LoginExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;

/**
 * Created by HOME on 03-Dec-15.
 */
public class ResultActivity extends Activity implements LoginDone {

    String value;
    TextView title;
    ListView list;
    TextView txt;
    String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        title=(TextView)findViewById(R.id.txtTitleValue);
        list=(ListView)findViewById(R.id.listResult);
        txt=(TextView)findViewById(R.id.txtMessage);
        value=getIntent().getStringExtra("value");
        String secId=getIntent().getStringExtra("sid");
        String classId=getIntent().getStringExtra("cid");

        if(value.equals("info")){
            title.setText("Student Record");
            new LoginExecute(ResultActivity.this, MyConfig.Serverurl + "api_Teacher/getStudent.php?sid="+secId+"&cid="+classId).execute();

        }else if(value.equals("transport")){
            title.setText("Student Transport Record");
            new LoginExecute(ResultActivity.this, MyConfig.Serverurl + "api_Teacher/getTransportation.php?sid="+secId+"&cid="+classId).execute();
        } else if(value.equals("fee")){
            title.setText("Student Fee Record");
            new LoginExecute(ResultActivity.this,MyConfig.Serverurl + "api_Teacher/getStuFee.php?sid="+secId+"&cid="+classId).execute();
        }
    }

    @Override
    public void getResult(String s) {
        if(value.equals("info")){
            Log.d("student information", s);
            ArrayList<TeacherInfoItem> listItem=new ArrayList<>();
           try {

               JSONArray jArray = new JSONArray(s);
               JSONObject jObject = null;

               for (int i = 0; i < jArray.length(); i++) {
                   jObject = jArray.getJSONObject(i);
                    status = jObject.getString("status");
                   if(status.equals("0")){

                   }else {
                       String cardId = jObject.getString("cardid");
                       String name = jObject.getString("name");
                       String RollNo = jObject.getString("Rollno");

                       listItem.add(new TeacherInfoItem(cardId, name, RollNo));
                   }
               }
               if(status.equals("1")){
               list.setAdapter(new StudentInfoAdapter(ResultActivity.this, listItem));
           }else{
               list.setVisibility(ListView.GONE);
               txt.setVisibility(View.VISIBLE);
               txt.setText("No Information Found");
           }
           }catch (Exception e){
Log.d("exe",e.getMessage());
           }


        }else if(value.equals("fee")){
            ArrayList<TeacherFeeItem> listItem=new ArrayList<>();
            Log.d("checker", s);
            try {
                JSONObject jsonResponse=new JSONObject(s);
                JSONArray cast = jsonResponse.getJSONArray("W");
                for (int i = 0; i < cast.length(); i++) {
                    JSONObject actor = cast.getJSONObject(i);

                    String  carId = actor.getString("cardid");
                    String  rollNo = actor.getString("Rollno");
                    String  name = actor.getString("name");
                    String  status = actor.getString("Status");
                    listItem.add(new TeacherFeeItem(carId,rollNo,name,status));
                }

                list.setAdapter(new StudentFeeAdapter(ResultActivity.this,listItem));
            }catch (Exception e){

            }

        } else if(value.equals("transport")){
            ArrayList<TeacherTransportItem> listItem=new ArrayList<>();
            Log.d("checker", s);
            try {
                JSONArray jArray = new JSONArray(s);
                JSONObject actor = null;
                for (int i = 0; i < jArray.length(); i++) {
                     actor = jArray.getJSONObject(i);

                    String  carId = actor.getString("cardid");
                    String  rollNo = actor.getString("Rollno");
                    String  name = actor.getString("name");
                    String  status = actor.getString("Transport");



                    listItem.add(new TeacherTransportItem(carId,rollNo,name,status));
                }

                list.setAdapter(new StudenTRansportAdapter(ResultActivity.this,listItem));
            }catch (Exception e){
                Log.d("esed", e.getMessage());
            }


        }
    }
}
