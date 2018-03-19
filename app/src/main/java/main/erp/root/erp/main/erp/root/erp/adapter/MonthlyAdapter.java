package main.erp.root.erp.main.erp.root.erp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.MonthlyItem;

/**
 * Created by root on 11/30/15.
 */
public class MonthlyAdapter extends BaseAdapter {

    Context activity;
    ArrayList<MonthlyItem> result = new ArrayList<>();
    TextView txtDay, txtStatus,txtdayname;
    LinearLayout linstatus;
    int positiongd;
    TextView day, status;

    public MonthlyAdapter(Context context, ArrayList<MonthlyItem> arrayVehicle) {
        result = arrayVehicle;
        activity = context;
       // positiongd =pos;

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
        convertView = inf.inflate(R.layout.row_monthly, null);

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        txtDay = (TextView) convertView.findViewById(R.id.row_day);
       //txtStatus = (TextView) convertView.findViewById(R.id.row_status);
        linstatus=(LinearLayout)convertView.findViewById(R.id.linstatus);
      // txtdayname=(TextView)convertView.findViewById(R.id.txtdayname);
       // String a = String.valueOf(positiongd);
       // Log.d("Position",a);
       // position = positiongd;

                txtDay.setText(result.get(position).getDay());
                txtDay.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                if (result.get(position).getStatus().equalsIgnoreCase("A")) {
                    linstatus.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.circle_absent));
                } else if (result.get(position).getStatus().equalsIgnoreCase("H")) {
                    linstatus.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.circle_holiday));
                } else if (result.get(position).getStatus().equalsIgnoreCase("L")) {
                    linstatus.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.circle_absent));
                } else if (result.get(position).getStatus().equalsIgnoreCase("PR"))
                {
                    txtDay.setTextColor(Color.GRAY);
                    linstatus.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.circle_past));

                }
                else if (result.get(position).getStatus().equalsIgnoreCase("F"))
                {
                    txtDay.setTextColor(Color.BLACK);

                }
                else if (result.get(position).getStatus().equalsIgnoreCase("P")){
                    linstatus.setBackgroundDrawable(activity.getResources().getDrawable(R.drawable.circle_present));
                }

        //txtdayname.setText(result.get(position).getDayname());
      //  txtStatus.setText(result.get(position).getStatus());






        return convertView;
    }
}
