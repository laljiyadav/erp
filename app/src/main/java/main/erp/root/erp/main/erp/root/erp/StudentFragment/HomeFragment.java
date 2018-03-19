package main.erp.root.erp.main.erp.root.erp.StudentFragment;

/**
 * Created by Ravi on 29/07/15.
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.adapter.HomeAdapter;
import main.erp.root.erp.main.erp.root.erp.execute.studentExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;


public class HomeFragment extends Fragment implements RefresList {

    GridView geidH;
    private SharedPreferences mPrefs;
    String cid,clsId,std_id,sacId;
    LoginDone c;
    Context context;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_home, container, false);
        geidH = (GridView) rootView.findViewById(R.id.gridHome);
        geidH.setAdapter(new HomeAdapter(context));
        mPrefs   =getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        cid=mPrefs.getString("cid", "");
        clsId=mPrefs.getString("clsid", "");
        std_id=mPrefs.getString("std_id", "");
        sacId=mPrefs.getString("secid", "");
        geidH.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          displayView(position);
            }
        });

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

    }


    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {

            case 0:
                fragment = new ProfileFragment();
                new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/getProfile.php?card_id=" + cid).execute();
                title = getString(R.string.title_profile);
                break;

            case 1:
                fragment = new MonthlyAttendance();
                new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/monthlyAttendance.php?cid=" + cid).execute();
                title = getString(R.string.title_monthly);

                break;

            case 2:
                fragment = new Circular_Fragment();
                new studentExecute(getActivity(),MyConfig.Serverurl + "api_Student/getMessage.php?Std_Pkey=" + std_id).execute();
                title = "Circular";
                break;

            case 3:

                fragment = new HomeWorkFragment();
                new studentExecute(getActivity(),MyConfig.Serverurl + "api_Student/getAssignment.php?cls_id=" + clsId + "&sec_id=" + sacId).execute();
                title = "HomeWork";
                break;
            case 4:

                fragment = new CalenderOptionFragment();
                title = "Calendar";
                break;


            case 5:
                fragment = new RmarkFragment();
                new studentExecute(getActivity(),MyConfig.Serverurl + "api_Student/getRemarks.php?card_id=" + cid).execute();
                title = "Remark";
                break;

            case 6:
                try {
                    fragment = new ResultFragment();
                    new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/getResult.php?Std_Pkey=" + std_id + "&cls_id=" + clsId).execute();
                } catch (Exception e) {
                    Log.d("exception", e.getMessage());
                }
                title = getString(R.string.title_result);
                break;

            case 7:
                try {
                    fragment = new FeeSubmitOption();
                    //fragment = new FeesDEtailAttendance();
                    //new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/getFee.php?cid=" + cid + "&cls_id=" + clsId).execute();
                } catch (Exception e) {
                    Log.d("exception", e.getMessage());
                }
                title = getString(R.string.title_fees);
                break;
            case 8:
                fragment = new TransportFragment();
                new studentExecute(getActivity(),MyConfig.Serverurl +  "api_Student/getTransportation.php?cid=" + cid).execute();
                title = getString(R.string.title_transport);
                break;
            case 9:
                fragment = new CommunicationFragment();
                title = "Communication";
                break;
            case 10:
                fragment = new LibraryFagment();
                new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/getLiebrarybook.php?cid=" + cid).execute();
                title = "Library";
                break;
            default:
                break;
        }

        if (fragment != null) {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.addToBackStack(title);
            fragmentTransaction.commit();
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        }
    }
}