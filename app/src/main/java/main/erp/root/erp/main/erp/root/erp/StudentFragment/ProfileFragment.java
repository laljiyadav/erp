package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

/**
 * Created by root on 11/27/15.
 */
public class ProfileFragment extends Fragment implements RefresList {

    LoginDone c;
    Context context;
    TextView txtN, txtS, txtC, txtrollno, txtfathername, txtmothername, txtno, txtemail, txtaddress1, txtadress2, txtaddress3, txtaddress4;
    ImageView imageView2;
    private ScrollView scrlparent;
    private TextView txtmsg;
    private TextView txtteacher;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        txtN = (TextView) rootView.findViewById(R.id.name);
        txtS = (TextView) rootView.findViewById(R.id.section);
        txtC = (TextView) rootView.findViewById(R.id.classN);
        txtrollno = (TextView) rootView.findViewById(R.id.txtrollno);
        txtfathername = (TextView) rootView.findViewById(R.id.txtfathername);
        txtmothername = (TextView) rootView.findViewById(R.id.txtmothername);
        txtno = (TextView) rootView.findViewById(R.id.txtno);
        txtemail = (TextView) rootView.findViewById(R.id.txtemail);
        imageView2 = (ImageView) rootView.findViewById(R.id.imageView2);

        txtaddress1 = (TextView) rootView.findViewById(R.id.txtaddress1);
        txtadress2 = (TextView) rootView.findViewById(R.id.txtadress2);
        txtaddress3 = (TextView) rootView.findViewById(R.id.txtaddress3);
        txtaddress4 = (TextView) rootView.findViewById(R.id.txtaddress4);
        txtmsg=(TextView)rootView.findViewById(R.id.txtmsg);
        txtteacher=(TextView)rootView.findViewById(R.id.txtteacher);
        scrlparent=(ScrollView)rootView.findViewById(R.id.scrlparent);

        // Inflate the layout for this fragment
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
            JSONObject jo = new JSONObject(s);
            //JSONArray jsonArray = jo.getJSONArray();
            String status=jo.getString("status");

            if(status.equals("0"))
            {
                scrlparent.setVisibility(View.GONE);
                txtmsg.setVisibility(View.VISIBLE);

            }
            else if(status.equals("1"))
            {
                JSONObject jobj = jo.getJSONObject("Student_info");

                String fName = jobj.getString("Fname");
                String mName = jobj.getString("Mname");
                String lName = jobj.getString("Lname");
                String class1 = jobj.getString("Class");
                String section = jobj.getString("Section");

                txtteacher.setText(jobj.getString("Teacher"));
                txtN.setText("Hi! I am " + fName + " " + lName);
                txtC.setText(class1);
                txtS.setText(section);
                txtrollno.setText(jobj.getString("Roll Number"));

                //JSONArray jsonArray1 = jo.getJSONArray("family_info");
                JSONObject jobj1 = jo.getJSONObject("family_info");

                txtfathername.setText(jobj1.getString("FatherName"));
                txtmothername.setText(jobj1.getString("MotherName"));

                //JSONArray jsonArray2 = jo.getJSONArray("address");
                JSONObject jobj2 = jo.getJSONObject("Address");

                txtno.setText(jobj2.getString("ParentMobileNo"));
                txtemail.setText(jobj2.getString("ParentEmailID"));

                txtaddress1.setText(jobj2.getString("Perm PermAddLine1"));
                txtadress2.setText(jobj2.getString("Perm State"));
                txtaddress3.setText(jobj2.getString("Perm City"));
                txtaddress4.setText(jobj2.getString("Perm Country"));

                //JSONArray jsonArray3 = jo.getJSONArray("image");
                JSONObject jobj3 = jo.getJSONObject("image");

                Picasso.with(getActivity()).load(jobj3.getString("Photo"))
                        .error(R.drawable.blankimage)
                        .placeholder(R.drawable.blankimage).skipMemoryCache()
                        .into(imageView2);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

