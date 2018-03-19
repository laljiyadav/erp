package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import main.erp.root.erp.R;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class CalenderHolder extends RecyclerView.ViewHolder {

    protected TextView txteventtype;
    protected TextView txteventname;
    protected TextView txteventdate;
    protected TextView txteventtime;
    protected TextView txtvennue;
    protected TextView txtinstruct;
    protected TextView txtname;


    public CalenderHolder(View view) {
        super(view);

        this.txteventtype = (TextView) view.findViewById(R.id.txteventtype);
        this.txteventname=(TextView)view.findViewById(R.id.txteventname);
        this.txteventdate=(TextView)view.findViewById(R.id.txteventdate);

        this.txteventtime = (TextView) view.findViewById(R.id.txteventtime);
        this.txtvennue=(TextView)view.findViewById(R.id.txtvennue);
        this.txtinstruct=(TextView)view.findViewById(R.id.txtinstruct);
        this.txtname=(TextView)view.findViewById(R.id.txtname);
    }
}
