package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Bean.Calendar_Bean;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.adapter.Calendar_Adapter;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

public class CalenderFragment extends Fragment implements RefresList {

    private RecyclerView mRecyclerView;
    public ArrayList<Calendar_Bean> category_beanArrayList = new ArrayList<Calendar_Bean>();
    Context context;
    LoginDone c;
    private TextView txtmsg;

    private Calendar_Adapter circular_adapter;

    public CalenderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calender, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        txtmsg=(TextView)rootView.findViewById(R.id.txtmsg);

        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        circular_adapter = new Calendar_Adapter(getActivity(), category_beanArrayList);
        mRecyclerView.setAdapter(circular_adapter);

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

            String status = jsonResponse.getString("status");

            if (status.equals("0")) {

                mRecyclerView.setVisibility(View.GONE);
                txtmsg.setVisibility(View.VISIBLE);

            } else if (status.equals("1")) {

                JSONArray jsonArray = jsonResponse.getJSONArray("Events");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject hwElement = jsonArray.getJSONObject(i);
                    Calendar_Bean homeWork_bean = new Calendar_Bean();
                    homeWork_bean.setEventid(hwElement.getString("Event Id"));
                    homeWork_bean.setEventtype(hwElement.getString("Event Type"));
                    homeWork_bean.setEventname(hwElement.getString("Event Name"));
                    homeWork_bean.setEventdate(hwElement.getString("Date"));
                    homeWork_bean.setEventtime(hwElement.getString("Time"));
                    homeWork_bean.setVenue(hwElement.getString("Venue"));
                    homeWork_bean.setContname(hwElement.getString("Contact Name"));
                    category_beanArrayList.add(homeWork_bean);
                }
                circular_adapter.notifyDataSetChanged();

            }

            /*Calendar calendar = Calendar.getInstance();
//date format is:  "Date-Month-Year Hour:Minutes am/pm"
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); //Date and time

            txtheaderdate.setText(sdf.format(calendar.getTime()));

            SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
            Date date = new Date();
            txtdayname.setText(sdf_.format(date));*/


        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }

    }
}
