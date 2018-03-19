package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Bean.TransportBean;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

/**
 * Created by root on 11/27/15.
 */
public class TransportFragment extends Fragment implements RefresList {
    LoginDone c;
    Context context;
    TextView txt1, txt2, txt3, txt4, txt5, txt6, txt7, txtpickuproute, txtpickupstop, txtdroproute, txtdropstop, txtstarttime, txtstoptime, txtfee;
    LinearLayout lint1, lint2, lint3;
    TextView txtheader;
    LinearLayout linparent;
    TextView txtmsg;

    ArrayList<TransportBean> transportBeen =new ArrayList<TransportBean>();

    public TransportFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_transportation, container, false);
        txt1 = (TextView) rootView.findViewById(R.id.txt1);
        txt2 = (TextView) rootView.findViewById(R.id.txt2);
        txt3 = (TextView) rootView.findViewById(R.id.txt3);
        txt4 = (TextView) rootView.findViewById(R.id.txt4);
        txt5 = (TextView) rootView.findViewById(R.id.txt5);
        txt6 = (TextView) rootView.findViewById(R.id.txt6);
        txt7 = (TextView) rootView.findViewById(R.id.txt7);
        txtpickuproute = (TextView) rootView.findViewById(R.id.txtpickuproute);
        txtpickupstop = (TextView) rootView.findViewById(R.id.txtpickupstop);
        txtdroproute = (TextView) rootView.findViewById(R.id.txtdroproute);
        txtdropstop = (TextView) rootView.findViewById(R.id.txtdropstop);
        txtstarttime = (TextView) rootView.findViewById(R.id.txtstarttime);
        txtstoptime = (TextView) rootView.findViewById(R.id.txtstoptime);
        txtfee = (TextView) rootView.findViewById(R.id.txtfee);
        lint1 = (LinearLayout) rootView.findViewById(R.id.lint1);
        lint2 = (LinearLayout) rootView.findViewById(R.id.lint2);
        lint3 = (LinearLayout) rootView.findViewById(R.id.lint3);
        txtheader=(TextView)rootView.findViewById(R.id.txtheader);
        linparent=(LinearLayout)rootView.findViewById(R.id.linparent);
        txtmsg=(TextView)rootView.findViewById(R.id.txtmsg);


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
        Log.d("kkkkkk", s);

        try {

           JSONObject jsonObject = new JSONObject(s);

            String status = jsonObject.getString("status");

            if (status.equals("0")) {
                linparent.setVisibility(View.GONE);
                txtmsg.setVisibility(View.VISIBLE);

            } else if (status.equals("1")) {

                JSONArray tranarray= jsonObject.getJSONArray("Transpotation");
                JSONObject jo = tranarray.getJSONObject(0);
                TransportBean transportitem = new TransportBean();
                transportitem.setStarttime(jo.getString("Start Time"));
                transportitem.setStoptime(jo.getString("End Time"));
                transportitem.setDrivername(jo.getString("Driver Name"));
                transportitem.setDrivermob(jo.getString("Mobile"));
                transportitem.setVehicalbrand(jo.getString("vehicle_brand"));
                transportitem.setVehicalno(jo.getString("vehicle_number"));
                transportitem.setRoute(jo.getString("route"));
                transportitem.setDestination(jo.getString("destination"));
                transportitem.setFee(jo.getString("fee"));
                transportBeen.add(transportitem);

                txt1.setText(transportBeen.get(0).getRoute());
                txt2.setText(transportBeen.get(0).getDestination());
                txt3.setText(transportBeen.get(0).getRoute());
                txt4.setText(transportBeen.get(0).getRoute());
                txt5.setText(transportBeen.get(0).getStarttime());
                txt6.setText(transportBeen.get(0).getStoptime());
                txt7.setText(transportBeen.get(0).getFee());

                lint1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt1.setText(transportBeen.get(0).getRoute());
                        txt2.setText(transportBeen.get(0).getDestination());
                        txt3.setText(transportBeen.get(0).getRoute());
                        txt4.setText(transportBeen.get(0).getRoute());
                        txt5.setText(transportBeen.get(0).getStarttime());
                        txt6.setText(transportBeen.get(0).getStoptime());
                        txt7.setText(transportBeen.get(0).getFee());

                        txt3.setVisibility(View.VISIBLE);
                        txt4.setVisibility(View.VISIBLE);
                        txt5.setVisibility(View.VISIBLE);
                        txt6.setVisibility(View.VISIBLE);
                        txt7.setVisibility(View.VISIBLE);
                        txtdroproute.setVisibility(View.VISIBLE);
                        txtdropstop.setVisibility(View.VISIBLE);
                        txtstarttime.setVisibility(View.VISIBLE);
                        txtstoptime.setVisibility(View.VISIBLE);
                        txtfee.setVisibility(View.VISIBLE);
                        txtpickuproute.setText("Pickup Route: ");
                        txtpickupstop.setText("Pickup Stop: ");

                        lint1.setBackgroundResource(R.drawable.t4);
                        lint2.setBackgroundResource(R.drawable.t2);
                        lint3.setBackgroundResource(R.drawable.t6);
                        txtheader.setText("BUS STOP DETAILS");

                    }
                });
                lint2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt1.setText(transportBeen.get(0).getDrivername());
                        txt2.setText(transportBeen.get(0).getDrivermob());
                        txt3.setVisibility(View.GONE);
                        txt4.setVisibility(View.GONE);
                        txt5.setVisibility(View.GONE);
                        txt6.setVisibility(View.GONE);
                        txt7.setVisibility(View.GONE);
                        txtpickuproute.setText("Driver Name: ");
                        txtpickupstop.setText("Driver Mobile: ");
                        txtdroproute.setVisibility(View.GONE);
                        txtdropstop.setVisibility(View.GONE);
                        txtstarttime.setVisibility(View.GONE);
                        txtstoptime.setVisibility(View.GONE);
                        txtfee.setVisibility(View.GONE);
                        lint1.setBackgroundResource(R.drawable.t1);
                        lint2.setBackgroundResource(R.drawable.t5);
                        lint3.setBackgroundResource(R.drawable.t6);
                        txtheader.setText("DRIVER DETAILS");
                    }
                });
                lint3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        txt1.setText(transportBeen.get(0).getVehicalbrand());
                        txt2.setText(transportBeen.get(0).getVehicalno());
                        txt3.setVisibility(View.GONE);
                        txt4.setVisibility(View.GONE);
                        txt5.setVisibility(View.GONE);
                        txt6.setVisibility(View.GONE);
                        txt7.setVisibility(View.GONE);
                        txtpickuproute.setText("Vehical Brand: ");
                        txtpickupstop.setText("Vehical Number: ");
                        txtdroproute.setVisibility(View.GONE);
                        txtdropstop.setVisibility(View.GONE);
                        txtstarttime.setVisibility(View.GONE);
                        txtstoptime.setVisibility(View.GONE);
                        txtfee.setVisibility(View.GONE);
                        lint1.setBackgroundResource(R.drawable.t1);
                        lint2.setBackgroundResource(R.drawable.t2);
                        lint3.setBackgroundResource(R.drawable.t3);
                        txtheader.setText("VEHICAL DETAILS");
                    }
                });
            }

        } catch (Exception e) {
            Log.d("transport_exception", e.getMessage());
        }
    }
}






