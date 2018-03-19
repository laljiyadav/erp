package main.erp.root.erp.main.erp.root.erp.TeacherFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.FeesDEtailAttendance;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.MonthlyAttendance;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.ProfileFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.TodayAttendanceFragment;
import main.erp.root.erp.main.erp.root.erp.StudentFragment.TransportFragment;
import main.erp.root.erp.main.erp.root.erp.Utils.CLassItem;
import main.erp.root.erp.main.erp.root.erp.Utils.SectionItem;
import main.erp.root.erp.main.erp.root.erp.activity.MainActivity;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.activity.TeacherActivity;
import main.erp.root.erp.main.erp.root.erp.adapter.HomeAdapter;
import main.erp.root.erp.main.erp.root.erp.adapter.TeacherHomeAdapter;
import main.erp.root.erp.main.erp.root.erp.execute.TeacherExecute;
import main.erp.root.erp.main.erp.root.erp.execute.studentExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.InfoDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;
import main.erp.root.erp.main.erp.root.erp.interfaces.TeacherDone;

/**
 * Created by root on 1/7/16.
 */
public class TeacherHome extends Fragment {

    GridView geidH;
    private SharedPreferences mPrefs;
    String classId,cls_id;
    InfoDone c;
    Context context;

    public TeacherHome() {
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
        geidH.setAdapter(new TeacherHomeAdapter(context));
        mPrefs   =getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        classId=mPrefs.getString("class_id", "");
        cls_id=mPrefs.getString("std_id", "");
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
      //  c = (InfoDone) activity;
     //   ((TeacherActivity) context).inf = this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }




    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {

            case 0:
                fragment = new TeacherProfile();
                title = "Teacher Profile";
                break;

            case 1:

                Bundle bundle = new Bundle();
                String myMessage = "info";
                bundle.putString("message", myMessage );
                fragment = new InfStudentFragment();
                fragment.setArguments(bundle);
                new TeacherExecute(getActivity(),"http://webfastfix.com/sipl/api_Teacher/getClass.php").execute();
                title = "Student Detail";
                break;
            case 2:
                Bundle bundle1 = new Bundle();
                String myMessage1 = "transport";
                bundle1.putString("message", myMessage1 );
                fragment = new InfStudentFragment();
                fragment.setArguments(bundle1);
                new TeacherExecute(getActivity(),"http://webfastfix.com/sipl/api_Teacher/getClass.php").execute();
                title = "Student Transport Information";

                break;
            case 3:
                Bundle bundle2 = new Bundle();

                bundle2.putString("message", "fee");
                fragment = new InfStudentFragment();
                fragment.setArguments(bundle2);
                new TeacherExecute(getActivity(),"http://webfastfix.com/sipl/api_Teacher/getClass.php").execute();
                title = "Student Fees";
                break;

            default:
                break;
        }

        if (fragment != null) {

            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(title);
        }
    }


}
