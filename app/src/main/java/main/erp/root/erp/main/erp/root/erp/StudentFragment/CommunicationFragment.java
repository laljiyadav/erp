package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.MyConfig;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.execute.studentExecute;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;


public class CommunicationFragment extends Fragment implements RefresList {

    LoginDone c;
    Context context;
    EditText edtemail, edtmobile, edtmsg;
    Button btnsubmit, btncancel;
    String classId,cls_id,cid;
    private SharedPreferences mPrefs;

    public CommunicationFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_communication, container, false);
        mPrefs   =getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        cid = mPrefs.getString("cid", "");
        classId=mPrefs.getString("class_id", "");
        cls_id=mPrefs.getString("std_id", "");

        edtemail = (EditText) rootView.findViewById(R.id.edtemail);
        edtmobile = (EditText) rootView.findViewById(R.id.edtmobile);
        edtmsg = (EditText) rootView.findViewById(R.id.edtmsg);

        btnsubmit = (Button) rootView.findViewById(R.id.btnsubmit);
        btncancel = (Button) rootView.findViewById(R.id.btncancel);


        btnsubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile=edtmobile.getText().toString();
                String email=edtemail.getText().toString();
                String msg=edtmsg.getText().toString();

                if(!TextUtils.isEmpty(mobile) && !TextUtils.isEmpty(email)  && !TextUtils.isEmpty(msg))
                {
                    new studentExecute(getActivity(), MyConfig.Serverurl + "api_Student/contactus.php?Std_Pkey=" + cid + "&mobile=" + edtmobile.getText().toString() + "&email=" + edtemail.getText().toString() + "&message=" + edtmsg.getText().toString()).execute();
                }
                else
                {
                    Toast.makeText(getActivity(),"Enter Required Field",Toast.LENGTH_LONG).show();
                }

            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                trans.remove(getChildFragmentManager().findFragmentByTag("Communication"));
                trans.commit();
                manager.popBackStack();
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
        try {

            JSONObject jsonResponse = new JSONObject(s);
            JSONArray jsonArray=jsonResponse.getJSONArray("W");
            JSONObject jsonObject=jsonArray.getJSONObject(0);
            String status=jsonObject.getString("Status");

            if (status.equals("0")) {
                Toast.makeText(getActivity(),"Data Not Saved Successfully.",Toast.LENGTH_SHORT).show();

            } else if (status.equals("1")) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(),"Data Sent Successfully",Toast.LENGTH_SHORT).show();

                       /* FragmentManager manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction trans = manager.beginTransaction();
                        trans.remove(getChildFragmentManager().findFragmentByTag("Communication"));
                        trans.commit();
                        manager.popBackStack();*/
                    }
                });


            }


        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }


    }
}
