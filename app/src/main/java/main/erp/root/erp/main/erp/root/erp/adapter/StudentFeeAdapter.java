package main.erp.root.erp.main.erp.root.erp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.Utils.TeacherFeeItem;
import main.erp.root.erp.main.erp.root.erp.Utils.TeacherInfoItem;
import main.erp.root.erp.main.erp.root.erp.activity.ResultActivity;

/**
 * Created by root on 12/4/15.
 */
public class StudentFeeAdapter extends BaseAdapter {
    Context activity;
    TextView name,rollNo,status;
    ArrayList<TeacherFeeItem> result=new ArrayList<>();
    public StudentFeeAdapter(ResultActivity resultActivity, ArrayList<TeacherFeeItem> listItem) {

        activity=resultActivity;
        result=listItem;




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
        convertView = inf.inflate(R.layout.teacher_fee, null);
        name=(TextView)convertView.findViewById(R.id.studentNameTeacher);
        rollNo=(TextView)convertView.findViewById(R.id.studentRollNo);
        status=(TextView)convertView.findViewById(R.id.statusFeesTeacher);
      TextView  tit=(TextView)convertView.findViewById(R.id.teacherTitle);

        tit.setText("Fee for Roll No."+result.get(position).getRollNo());
        name.setText(result.get(position).getName());
        rollNo.setText(result.get(position).getRollNo());
        status.setText(result.get(position).getStatus());



        return convertView;
    }
}
