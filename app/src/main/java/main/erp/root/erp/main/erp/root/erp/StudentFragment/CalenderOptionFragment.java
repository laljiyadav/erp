package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.execute.studentExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

import static android.R.attr.fragment;


/**
 * Created by Er Lalji Yadav on 6/7/2017.
 */

public class CalenderOptionFragment extends Fragment{
    Context context;
    private SharedPreferences mPrefs;
    LinearLayout btn_past, btn_present,btn_future;
    LoginDone c;
    String cid,clsId,std_id,sacId;
    public CalenderOptionFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_option_calender, container, false);
        mPrefs   =getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        cid=mPrefs.getString("cid", "");
        std_id=mPrefs.getString("std_id", "");
        clsId=mPrefs.getString("clsid", "");
        sacId=mPrefs.getString("secid", "");
        btn_past= (LinearLayout) rootView.findViewById(R.id.past_event);
        btn_present= (LinearLayout) rootView.findViewById(R.id.present_event);
        btn_future= (LinearLayout) rootView.findViewById(R.id.future_event);
        btn_past.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CalenderFragment());
                new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/getPastEvents.php?cls_id=" + clsId + "&sec_id=" + sacId).execute();

            }
        });
        btn_present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new CalenderFragment());
                new studentExecute(getActivity(),MyConfig.Serverurl+"api_Student/gettodayEvents.php?cls_id=" + clsId +"&sec_id="+sacId).execute();
            }
        });
        btn_future.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                replaceFragment(new CalenderFragment());
                new studentExecute(getActivity(),MyConfig.Serverurl+"api_Student/getUpcomingEvents.php?cls_id=" + clsId +"&sec_id="+sacId).execute();
            }
        });
        return rootView;
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

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
