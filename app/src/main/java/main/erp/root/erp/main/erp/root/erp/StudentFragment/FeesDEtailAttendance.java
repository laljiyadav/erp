package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.FeeItem;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.adapter.FeesAdapter;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

/**
 * Created by root on 11/27/15.
 */
public class FeesDEtailAttendance extends Fragment implements RefresList {


    LoginDone c;
    Context context;
    ListView feeslIST;
    ArrayList<FeeItem> arrayVehicle = new ArrayList<>();
    private TextView txtmsg;

    public FeesDEtailAttendance() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fees, container, false);
        feeslIST = (ListView) rootView.findViewById(R.id.feesList);
        txtmsg = (TextView) rootView.findViewById(R.id.txtmsg);
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
        //Log.d("kkkkkk", s);
        try {

            arrayVehicle.clear();
            JSONObject jsonResponse = new JSONObject(s);
            String status = jsonResponse.getString("status");
            if (status.equalsIgnoreCase("1")) {
                JSONArray cast = jsonResponse.getJSONArray("Fee");
                for (int i = 0; i < cast.length(); i++) {
                    JSONObject actor = cast.getJSONObject(i);

                    String feeMonth = actor.getString("Month");
                    String tutionFess = actor.getString("Tution Fee");
                    String gameFees = actor.getString("Game Fee");
                    String computerFees = actor.getString("Computer Fee");
                    String examFees = actor.getString("Exam Fee");
                    String otherFees = actor.getString("Other Fee");
                    String lateFees = actor.getString("Late Fee");
                    String totalFees = actor.getString("Total Fee");
                    arrayVehicle.add(new FeeItem(feeMonth, tutionFess, gameFees, computerFees, examFees, otherFees, lateFees, totalFees, status));

                }
                Log.d("detail_student", String.valueOf(arrayVehicle.size()));
                feeslIST.setAdapter(new FeesAdapter(context, arrayVehicle));
            } else if (status.equals("0")) {

                feeslIST.setVisibility(View.GONE);
                txtmsg.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }
    }
}





