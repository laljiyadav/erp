package main.erp.root.erp.main.erp.root.erp.TeacherFragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import main.erp.root.erp.R;

/**
 * Created by root on 12/2/15.
 */
public class TeacherProfile extends Fragment {

    TextView txtN,txtS,txtC;
    private SharedPreferences mPrefs;

    public TeacherProfile() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.teacher_profile, container, false);
        txtN=(TextView)rootView.findViewById(R.id.teacherName);
        txtS=(TextView)rootView.findViewById(R.id.empId);
       // txtC=(TextView)rootView.findViewById(R.id.classN);


        mPrefs   =getActivity().getSharedPreferences("teacher", Context.MODE_PRIVATE);
       String   fName=mPrefs.getString("first_name", "");
        String  mName=mPrefs.getString("middle_name","");
        String    empId=mPrefs.getString("empId","");
        txtS.setText("EMP ID:"+empId);

        txtN.setText(fName+" "+mName);

        // Inflate the layout for this fragment
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}


