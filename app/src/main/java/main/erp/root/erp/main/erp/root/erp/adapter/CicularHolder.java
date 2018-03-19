package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import main.erp.root.erp.R;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class CicularHolder extends RecyclerView.ViewHolder {

    protected TextView txttitle;
    protected TextView txtmessage;
    protected TextView txtduedate;


    public CicularHolder(View view) {
        super(view);

        this.txttitle = (TextView) view.findViewById(R.id.txttitleval);
        this.txtmessage=(TextView)view.findViewById(R.id.txtmeassage);
        this.txtduedate=(TextView)view.findViewById(R.id.txtduedate);
    }
}
