package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import main.erp.root.erp.R;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class ResultHolder extends RecyclerView.ViewHolder {

    protected TextView txtsubject;
    protected TextView txttotmarks;
    protected TextView txtwmarks;
    protected TextView txtpmarks;
    protected TextView txtamarks;


    public ResultHolder(View view) {
        super(view);

        this.txtsubject = (TextView) view.findViewById(R.id.txtsubject);
        this.txttotmarks=(TextView)view.findViewById(R.id.txttotmarks);
        this.txtpmarks=(TextView)view.findViewById(R.id.txtpmarks);
        this.txtwmarks=(TextView)view.findViewById(R.id.txtwmarks);
        this.txtamarks=(TextView)view.findViewById(R.id.txtamarks);
    }
}
