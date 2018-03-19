package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

/**
 * Created by root on 1/7/16.
 */
public class AssignmentFragment extends Fragment implements RefresList {
    LoginDone c;
    Context context;
    TextView txt;
    public AssignmentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.assign, container, false);
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

       /* try {

            String jsonString = s.substring(1, s.length() - 1);

            JSONObject jo = new JSONObject(jsonString);

            String status =jo.getString("status");

            if(status.equals("0")){
                txt.setText("No Vehicle has assigned");
            } else   if(status.equals("1")){
                txt.setText("vehicle has assigned");
            }

        }catch (Exception e){
            Log.d("transport_exception",e.getMessage());
        }*/
    }
}

