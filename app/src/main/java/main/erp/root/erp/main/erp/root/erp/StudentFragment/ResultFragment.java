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
import main.erp.root.erp.main.erp.root.erp.Bean.Result_Bean;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.adapter.Result_Adapter;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;
import main.erp.root.erp.main.erp.root.erp.interfaces.RefresList;

public class ResultFragment extends Fragment implements RefresList {

    private RecyclerView mRecyclerView;
    public ArrayList<Result_Bean> category_beanArrayList = new ArrayList<Result_Bean>();
    Context context;
    LoginDone c;
    private Result_Adapter circular_adapter;
    private TextView txtmsg;

    public ResultFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_result, container, false);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.cardList);
        txtmsg=(TextView)rootView.findViewById(R.id.txtmsg);

        mRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        circular_adapter = new Result_Adapter(getActivity(), category_beanArrayList);
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

                JSONArray jsonArray = jsonResponse.getJSONArray("Result");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject hwElement = jsonArray.getJSONObject(i);
                    Result_Bean homeWork_bean = new Result_Bean();
                    homeWork_bean.setSubname(hwElement.getString("SUB Name"));
                    homeWork_bean.setTotalmarks(hwElement.getString("totalMarks"));
                    homeWork_bean.setWmarks(hwElement.getString("wMarks"));
                    homeWork_bean.setPmarks(hwElement.getString("pMarks"));
                    homeWork_bean.setAmarks(hwElement.getString("aMarks"));
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
