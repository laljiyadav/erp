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

import org.json.JSONArray;
import org.json.JSONObject;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;


public class RmarkFragment extends Fragment implements RefresList {
    Context context;
    LoginDone c;
    TextView txtremark;

    public RmarkFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rmark, container, false);
        txtremark=(TextView)rootView.findViewById(R.id.txtremark);
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

            JSONObject jsonResponse = new JSONObject(s);
            String status = jsonResponse.getString("status");

            if (status.equals("0")) {

                txtremark.setText("Data Not Available...");

            } else if (status.equals("1")) {

                JSONArray tranarray= jsonResponse.getJSONArray("Msg");
                JSONObject jo = tranarray.getJSONObject(0);
                txtremark.setText(jo.getString("Remarks"));
            }



        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }


    }

}
