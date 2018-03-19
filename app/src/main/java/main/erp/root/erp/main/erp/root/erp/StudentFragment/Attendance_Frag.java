package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import main.erp.root.erp.R;

/**
 * Created by Vibhu on 26-03-2017.
 */

public class Attendance_Frag extends Fragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.attendence_layout, container, false);
        return rootView;
    }
}
