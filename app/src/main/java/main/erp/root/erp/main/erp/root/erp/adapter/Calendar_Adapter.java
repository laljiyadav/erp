package main.erp.root.erp.main.erp.root.erp.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Bean.Calendar_Bean;

/**
 * Created by Vibhu on 31-05-2016.
 */
public class Calendar_Adapter extends RecyclerView.Adapter {

    private List<Calendar_Bean> mHomeListItem;
    private FragmentActivity mactivity;


    public Calendar_Adapter(FragmentActivity activity, List<Calendar_Bean> home_beans) {

        this.mactivity=activity;
        this.mHomeListItem=home_beans;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final RecyclerView.ViewHolder mh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_item,parent, false);
        mh = new CalenderHolder(v);
        return mh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final Calendar_Bean homewokitem = mHomeListItem.get(position);

        ((CalenderHolder) holder).txteventtype.setText(homewokitem.getEventtype());
        ((CalenderHolder) holder).txteventname.setText(homewokitem.getEventname());
        ((CalenderHolder) holder).txteventdate.setText(homewokitem.getEventdate());
        ((CalenderHolder) holder).txteventtime.setText(homewokitem.getEventtime());
        ((CalenderHolder) holder).txtvennue.setText(homewokitem.getVenue());
        ((CalenderHolder) holder).txtinstruct.setText(homewokitem.getInstruction());
        ((CalenderHolder) holder).txtname.setText(homewokitem.getContname());

    }

    @Override
    public int getItemCount() {
        return (null != mHomeListItem ? mHomeListItem.size() : 0);
    }


}
