package main.erp.root.erp.main.erp.root.erp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import main.erp.root.erp.R;
import main.erp.root.erp.main.erp.root.erp.interfaces.EventReceived;

/**
 * Created by root on 1/7/16.
 */
public class EventActivity extends AppCompatActivity implements EventReceived {

    ListView eventList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        eventList=(ListView)findViewById(R.id.eventList);

    }

    @Override
    public void getResult(String s) {
        Log.d("student information", s);
    }
}
