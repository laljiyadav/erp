package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import main.erp.root.erp.R;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class LibraryHolder extends RecyclerView.ViewHolder {

    protected TextView txttitleval;
    protected TextView txtissuedate;
    protected TextView txtduedate;


    public LibraryHolder(View view) {
        super(view);

        this.txttitleval = (TextView) view.findViewById(R.id.txttitleval);
        this.txtissuedate=(TextView)view.findViewById(R.id.txtissuedate);
        this.txtduedate=(TextView)view.findViewById(R.id.txtduedate);
    }
}
