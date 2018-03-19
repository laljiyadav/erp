package main.erp.root.erp.main.erp.root.erp.TeacherFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.CLassItem;
import main.erp.root.erp.main.erp.root.erp.Utils.SectionItem;
import main.erp.root.erp.main.erp.root.erp.activity.ResultActivity;
import main.erp.root.erp.main.erp.root.erp.activity.TeacherActivity;
import main.erp.root.erp.main.erp.root.erp.adapter.SectionAdapter;
import main.erp.root.erp.main.erp.root.erp.adapter.classAdapter;
import main.erp.root.erp.main.erp.root.erp.execute.SectionExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.InfoDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.TeacherDone;


/**
 * Created by Ravi on 29/07/15.
 */
public class InfStudentFragment extends Fragment implements InfoDone{

    TeacherDone c;

    Button submit;
    String secId;
    String idValue;
    Context context;
    Spinner spinClass,spinSec;
    ArrayList<CLassItem>  result=new ArrayList<>();
    ArrayList<SectionItem>  secRe=new ArrayList<>();
    public InfStudentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_classwise, container, false);
        Bundle bundle = this.getArguments();
        final String myValue = bundle.getString("message");

        Log.d("read", myValue);

        spinClass=(Spinner)rootView.findViewById(R.id.spinClass);
        spinSec=(Spinner)rootView.findViewById(R.id.spinSection);
        submit=(Button)rootView.findViewById(R.id.infSubmit);

        spinClass.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 idValue=result.get(position).getId();
                c.sendSEctionData(idValue);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinSec.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 secId=secRe.get(position).getSid();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in=new Intent(context,ResultActivity.class);
                in.putExtra("value",myValue);
                in.putExtra("sid",secId);
                in.putExtra("cid",idValue);
                startActivity(in);



            }
        });
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context=getActivity();
        c =(TeacherDone)activity;
        ((TeacherActivity)context).inf=this;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void Done(ArrayList<CLassItem> s) {
        result=s;
        spinClass.setAdapter(new classAdapter(context,s));



    }

    @Override
    public void SEcDone(ArrayList<SectionItem> sectionREsult) {

        secRe=sectionREsult;


        spinSec.setAdapter(new SectionAdapter(context,sectionREsult));



    }


}
