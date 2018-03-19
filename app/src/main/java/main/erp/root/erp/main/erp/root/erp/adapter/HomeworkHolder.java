package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import main.erp.root.erp.R;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class HomeworkHolder extends RecyclerView.ViewHolder {

    protected TextView txttitle;
    protected TextView txttitleval;
    protected TextView txtdesc;
    protected TextView txtduedate;


    public HomeworkHolder(View view) {
        super(view);

        this.txttitle = (TextView) view.findViewById(R.id.txttitle);
        this.txttitleval=(TextView)view.findViewById(R.id.txttitleval);
        this.txtdesc=(TextView)view.findViewById(R.id.txtdesc);
        this.txtduedate=(TextView)view.findViewById(R.id.txtduedate);
    }
}
