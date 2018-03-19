package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Bean.Library_Bean;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class Library_Adapter extends RecyclerView.Adapter {

    private List<Library_Bean> mHomeListItem;
    private FragmentActivity mactivity;


    public Library_Adapter(FragmentActivity activity, List<Library_Bean> home_beans) {

        this.mactivity=activity;
        this.mHomeListItem=home_beans;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder mh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.library_item,parent, false);
        mh = new LibraryHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final Library_Bean homewokitem = mHomeListItem.get(position);
        ((LibraryHolder) holder).txttitleval.setText(homewokitem.getTitle());
        ((LibraryHolder) holder).txtissuedate.setText(homewokitem.getIssuedate());
        ((LibraryHolder) holder).txtduedate.setText(homewokitem.getDuedate());
    }

    @Override
    public int getItemCount() {
        return (null != mHomeListItem ? mHomeListItem.size() : 0);
    }


}
