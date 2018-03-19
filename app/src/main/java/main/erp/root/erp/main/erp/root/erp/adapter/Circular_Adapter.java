package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Bean.Circular_Bean;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class Circular_Adapter extends RecyclerView.Adapter {

    private List<Circular_Bean> mHomeListItem;
    private FragmentActivity mactivity;


    public Circular_Adapter(FragmentActivity activity, List<Circular_Bean> home_beans) {

        this.mactivity=activity;
        this.mHomeListItem=home_beans;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder mh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.circular_item,parent, false);
        mh = new CicularHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final Circular_Bean homewokitem = mHomeListItem.get(position);
        ((CicularHolder) holder).txttitle.setText(homewokitem.getTitle());
        ((CicularHolder) holder).txtmessage.setText(homewokitem.getMessage());
        ((CicularHolder) holder).txtduedate.setText(homewokitem.getDatetext());
    }

    @Override
    public int getItemCount() {
        return (null != mHomeListItem ? mHomeListItem.size() : 0);
    }


}
