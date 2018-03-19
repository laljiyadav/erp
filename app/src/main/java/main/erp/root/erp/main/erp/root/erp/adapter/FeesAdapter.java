package main.erp.root.erp.main.erp.root.erp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.FeeItem;

/**
 * Created by root on 11/30/15.
 */
public class FeesAdapter extends BaseAdapter {

    Context activity;
    ArrayList<FeeItem> result=new ArrayList<>();

    TextView txtStatus,txtFeeMonth,txtTutFee,txtSportFee,txtComputerFee,txtOtherFee,txtLateFee,txtExamFee;
    TextView txtTotal,feesDetails;

    public FeesAdapter(Context context, ArrayList<FeeItem> arrayVehicle) {
        activity=context;
        result=arrayVehicle;
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inf.inflate(R.layout.row_fees, null);


        feesDetails = (TextView) convertView.findViewById(R.id.feesDetail);

        //txtFeeMonth = (TextView) convertView.findViewById(R.id.txtFeeMonth);
        txtTutFee = (TextView) convertView.findViewById(R.id.txtTutionFee);
        txtSportFee = (TextView) convertView.findViewById(R.id.txtSportFee);
        txtComputerFee = (TextView) convertView.findViewById(R.id.txtComputerFee);
        txtTotal= (TextView) convertView.findViewById(R.id.txtTotal);
        txtExamFee = (TextView) convertView.findViewById(R.id.txtExamFee);
        txtOtherFee = (TextView) convertView.findViewById(R.id.txtOtherFee);
        txtLateFee = (TextView) convertView.findViewById(R.id.txtLateFEee);
        txtTutFee.setText(result.get(position).getTutionFess());
       // txtFeeMonth.setText(result.get(position).getFeeMonth());
        txtSportFee.setText(result.get(position).getGameFees());
        txtComputerFee.setText(result.get(position).getComputerFees());

        /*if(result.get(position).getStatus().equalsIgnoreCase("Due")){
            txtStatus.setTextColor(Color.parseColor("#ff0000"));
        }else{
            txtStatus.setTextColor(Color.parseColor("#06B25C"));
        }*/

        txtExamFee.setText(result.get(position).getExamFees());

        txtOtherFee.setText(result.get(position).getOtherFees());

        txtTotal.setText(result.get(position).getTotalFees());

        txtLateFee.setText(result.get(position).getLateFees());

        feesDetails.setText("Fees Details for the "+result.get(position).getFeeMonth());
        return convertView;
    }
}
