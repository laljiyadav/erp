package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Bean.Result_Bean;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class Result_Adapter extends RecyclerView.Adapter {

    private List<Result_Bean> mHomeListItem;
    private FragmentActivity mactivity;


    public Result_Adapter(FragmentActivity activity, List<Result_Bean> home_beans) {

        this.mactivity=activity;
        this.mHomeListItem=home_beans;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder mh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item,parent, false);
        mh = new ResultHolder(v);

        return mh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final Result_Bean homewokitem = mHomeListItem.get(position);
        ((ResultHolder) holder).txtsubject.setText(homewokitem.getSubname());
        ((ResultHolder) holder).txttotmarks.setText(homewokitem.getTotalmarks());
        ((ResultHolder) holder).txtwmarks.setText(homewokitem.getWmarks());
        ((ResultHolder) holder).txtpmarks.setText(homewokitem.getPmarks());
        ((ResultHolder) holder).txtamarks.setText(homewokitem.getAmarks());

    }


    @Override
    public int getItemCount() {
        return (null != mHomeListItem ? mHomeListItem.size() : 0);
    }


}
