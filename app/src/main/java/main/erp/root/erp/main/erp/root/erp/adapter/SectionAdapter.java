package main.erp.root.erp.main.erp.root.erp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.CLassItem;
import main.erp.root.erp.main.erp.root.erp.Utils.SectionItem;

/**
 * Created by root on 12/3/15.
 */
public class SectionAdapter extends BaseAdapter {

    ArrayList<SectionItem> result=new ArrayList<>();
    Context activity;
    public SectionAdapter(Context context, ArrayList<SectionItem> s) {

        result=s;
        activity=context;



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
        convertView = inf.inflate(R.layout.txt, null);
        TextView txt=(TextView)convertView.findViewById(R.id.row_txt);
        txt.setText(result.get(position).getSecName()
        );




        return convertView;
    }
}

