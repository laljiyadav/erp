package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.MonthlyItem;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.adapter.MonthlyAdapter;
import main.erp.root.erp.main.erp.root.erp.execute.studentExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

/**
 * Created by root on 11/27/15.
 */
public class MonthlyAttendance extends Fragment implements RefresList {

    Context context;
    LoginDone c;
    GridView grid;
    ArrayList<MonthlyItem> arrayVehicle = new ArrayList<>();
    TextView txtMonth;
    String cid;
    ImageView nxt_month,pre_month;
    //String cid="1001";
   // private RelativeLayout relparent;
    private TextView txtpresent, txtabsent, txtholiday;
    private SharedPreferences mPrefs;
    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
    static int previous_month=0;
    //static int next_month=0;
    int current_month;
    public MonthlyAttendance() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_monthly, container, false);
        mPrefs = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        cid = mPrefs.getString("cid", "");
        grid = (GridView) rootView.findViewById(R.id.gridMonth);
        txtMonth = (TextView) rootView.findViewById(R.id.txtMonth);
        nxt_month= (ImageView) rootView.findViewById(R.id.next_month);
        pre_month= (ImageView) rootView.findViewById(R.id.prev_month);
        pre_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(previous_month>=1)
                {
                    previousMonth();
                    Toast.makeText(context,"Previous Month",Toast.LENGTH_SHORT).show();
                }
                else if (previous_month<1)
                {
                    Toast.makeText(context,"No Previous Month",Toast.LENGTH_SHORT).show();
                }

            }
        });
        nxt_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (current_month==previous_month)
                {
                    Toast.makeText(context,"Current Month",Toast.LENGTH_LONG).show();
                }
                else if(previous_month<=current_month){
                    nextMonth();
                    Toast.makeText(context,"Next Month",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(context,"No Data Of Future Month",Toast.LENGTH_LONG).show();
                }

            }
        });
      //  relparent = (RelativeLayout) rootView.findViewById(R.id.relparent);
        txtpresent = (TextView) rootView.findViewById(R.id.txtpresent);
        txtabsent = (TextView) rootView.findViewById(R.id.txtabsent);
        txtholiday = (TextView) rootView.findViewById(R.id.txtholiday);
        current_month=calendar.get(Calendar.MONTH)+1;
        previous_month=current_month;

       // next_month=calendar.get(Calendar.MONTH)+1;
        Integer position = (Integer)grid.getTag();
        String a = String.valueOf(position);
        Log.d("GridPosition",a);

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        context = getActivity();
        c = (LoginDone) activity;
        ((StudentActivity) context).refresh = this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void getRefreshDone(String s) {
        try {

            arrayVehicle.clear();
            JSONObject jsonResponse = new JSONObject(s);
            JSONArray cast = jsonResponse.getJSONArray("Attendance");
            for (int i = 0; i < cast.length(); i++) {

                JSONObject actor = cast.getJSONObject(i);
                MonthlyItem monthlyItem = new MonthlyItem();
                monthlyItem.setDay(actor.getString("Day").substring(0, 2));
                //monthlyItem.setDayname(actor.getString("dayname"));
                //monthlyItem.setDay(actor.getString("Day"));
                //monthlyItem.setDayname(actor.getString("dayname"));
                monthlyItem.setStatus(actor.getString("Status"));

                arrayVehicle.add(monthlyItem);
            }

            txtpresent.setText("" + jsonResponse.getString("Present Total"));
            txtabsent.setText("" + jsonResponse.getString("Absent Total"));
            txtholiday.setText("" + jsonResponse.getString("Holiday Total"));
            txtMonth.setText(""+cast.getJSONObject(9).getString("Day").substring(2));
            grid.setAdapter(new MonthlyAdapter(context, arrayVehicle));

        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }
    }
    public void previousMonth()
    {
        previous_month--;
        String s = String.valueOf(previous_month);
        Log.d("month",s);
        Log.d("Cid",cid);
        new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/past_Attendance.php?cid="+cid+"&datemonth="+"0"+previous_month+"-2017").execute();

    }
    public void nextMonth()
    {
        previous_month++;
        String s = String.valueOf(previous_month);
        Log.d("month",s);
        Log.d("Cid",cid);
        new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/past_Attendance.php?cid="+cid+"&datemonth="+"0"+previous_month+"-2017").execute();



    }


}




