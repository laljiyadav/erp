package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONObject;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

/**
 * Created by root on 11/27/15.
 */
public class TodayAttendanceFragment extends Fragment implements RefresList {

    LoginDone c;
    Context context;
    ImageView img;
    TextView txtLabel;

    public TodayAttendanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_today, container, false);
        img=(ImageView)rootView.findViewById(R.id.image);
        txtLabel=(TextView)rootView.findViewById(R.id.label);


        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=getActivity();
        c =(LoginDone)activity;
        ((StudentActivity)context).refresh=this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void getRefreshDone(String s) {
        Log.d("kkkkkk", s);

        try {
            JSONObject jA = new JSONObject(s);
            String root=jA.getString("root");
            String newJson=root.substring(1, root.length() - 1);

            JSONObject jo=new JSONObject(newJson);
            String attendance=jo.getString("Attendance");

            if(attendance.equalsIgnoreCase("A")){
                img.setImageDrawable(getResources().getDrawable(R.drawable.happy));
                txtLabel.setText("Present Today");
            }else  if(attendance.equalsIgnoreCase("P")) {
                img.setImageDrawable(getResources().getDrawable(R.drawable.sad));
                txtLabel.setText("Absent Today");
            }

        }catch (Exception e){

        }



    }
}



