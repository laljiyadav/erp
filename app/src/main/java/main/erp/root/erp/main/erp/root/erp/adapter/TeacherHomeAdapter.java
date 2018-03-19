package main.erp.root.erp.main.erp.root.erp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import main.erp.root.erp.R;

/**
 * Created by root on 1/7/16.
 */
public class TeacherHomeAdapter extends BaseAdapter {

    Context c;
    TextView detail;
    ImageView imgg;
    int[] imageHome={R.drawable.profile,R.drawable.detail,R.drawable.transport,R.drawable.fee};
    String str[]={"Teacher Profile","Student Detail Classwise","Transport  Detail Classwise","Fee Detail Classwise"};

    public TeacherHomeAdapter(Context context) {
        c=context;
    }

    @Override
    public int getCount() {
        return imageHome.length;
    }

    @Override
    public Object getItem(int position) {
        return imageHome[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inf = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inf.inflate(R.layout.row_student, null);

        detail = (TextView) convertView.findViewById(R.id.roe_txtx);

        imgg= (ImageView) convertView.findViewById(R.id.row_image);

        detail.setText(str[position]);
        imgg.setImageResource(imageHome[position]);
        return convertView;
    }
}

