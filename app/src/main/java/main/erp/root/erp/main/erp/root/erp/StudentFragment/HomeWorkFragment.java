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
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Bean.HomeWork_Bean;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.adapter.Homework_Adapter;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

public class HomeWorkFragment extends Fragment implements RefresList {

    private RecyclerView mRecyclerView;
    public ArrayList<HomeWork_Bean> category_beanArrayList = new ArrayList<HomeWork_Bean>();
    private List<HomeWork_Bean> mHomeListItem;
    Context context;
    LoginDone c;
    private Homework_Adapter homeadapter;
    private TextView txtheaderdate, txtdayname;
    private LinearLayout linparent;
    private TextView txtmsg;

    public HomeWorkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home_work, container, false);
        txtheaderdate = (TextView) rootView.findViewById(R.id.txtheaderdate);
        txtdayname = (TextView) rootView.findViewById(R.id.txtdayname);
        linparent=(LinearLayout)rootView.findViewById(R.id.linparent);
        txtmsg=(TextView)rootView.findViewById(R.id.txtmsg);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        homeadapter = new Homework_Adapter(getActivity(), category_beanArrayList);
        mRecyclerView.setAdapter(homeadapter);

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
                linparent.setVisibility(View.GONE);
            } else if (status.equals("1")) {
                JSONArray jsonArray = jsonResponse.getJSONArray("Assignment");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject hwElement = jsonArray.getJSONObject(i);
                    HomeWork_Bean homeWork_bean = new HomeWork_Bean();
                    homeWork_bean.setSubject(hwElement.getString("Subject"));
                    homeWork_bean.setAssignmentname(hwElement.getString("Assignment Name"));
                    homeWork_bean.setDatetext(hwElement.getString("Date"));
                    homeWork_bean.setDescription(hwElement.getString("Description"));
                    category_beanArrayList.add(homeWork_bean);
                }
                homeadapter.notifyDataSetChanged();
            }
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy"); //Date and time
            txtheaderdate.setText(sdf.format(calendar.getTime()));
            SimpleDateFormat sdf_ = new SimpleDateFormat("EEEE");
            Date date = new Date();
            txtdayname.setText(sdf_.format(date));

        } catch (Exception e) {
            Log.d("exception", e.getMessage());
        }


    }
}
