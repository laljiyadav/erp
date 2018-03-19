package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.execute.studentExecute;


public class FeeSubmitOption extends Fragment {
private TextView txt_Online_Payment, txt_Per_Month;
    private String cid,clsId,std_id,sacId;
    private SharedPreferences mPrefs;

    public FeeSubmitOption() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_fee_submit_option, container, false);
        txt_Online_Payment= (TextView) layout.findViewById(R.id.txt_Online_Fee);
        txt_Per_Month= (TextView) layout.findViewById(R.id.txt_Per_month);
        mPrefs   =getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        cid=mPrefs.getString("cid", "");
        clsId=mPrefs.getString("clsid", "");
        std_id=mPrefs.getString("std_id", "");
        sacId=mPrefs.getString("secid", "");
        txt_Per_Month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    replaceFragment(new FeesDEtailAttendance());
                    new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/getFee.php?cid=" + cid + "&cls_id=" + clsId).execute();
                } catch (Exception e) {
                    Log.d("exception", e.getMessage());
                }

            }
        });


        txt_Online_Payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new PayNow());

            }
        });
        return layout;



    }
    private void replaceFragment(Fragment fragment)
    {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager= getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);
        if (!fragmentPopped){ //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.container_body, fragment);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }



}
