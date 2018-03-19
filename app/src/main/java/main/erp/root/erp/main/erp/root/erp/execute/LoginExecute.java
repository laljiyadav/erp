package main.erp.root.erp.main.erp.root.erp.execute;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import main.erp.root.erp.main.erp.root.erp.activity.LoginActivity;
import main.erp.root.erp.main.erp.root.erp.activity.ResultActivity;
import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;

/**
 * Created by ErLalji yadav on 26-Jul-15.
 */
public class LoginExecute extends AsyncTask<String,String,String> {


    LoginDone done;
    String urlPath;
    ProgressDialog pd;
    Context c;

    public LoginExecute(LoginActivity loginActivity, String url) {

      done=(LoginDone)loginActivity;
        urlPath=url;
        c=loginActivity;
    }

    public LoginExecute(FragmentActivity activity, String url) {

        done=(LoginDone)activity;
        c=(StudentActivity)activity;
        urlPath=url;
    }

    public LoginExecute(ResultActivity resultActivity, String s) {


        done=(LoginDone)resultActivity;
        urlPath=s;
        c=resultActivity;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(c);
        pd.setMessage("loading");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            URL url = new URL(urlPath);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader rd = new BufferedReader(new InputStreamReader(in));
            String line;
            StringBuffer response = new StringBuffer();
            while ((line = rd.readLine()) != null) {
                response.append(line);
            }
            Log.d("Response","DataResponse"+response);
            return response.toString();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {

        if(s==null){
            Log.d("Msg","login_response="+s);
           Toast.makeText(c, "Network Issue,Please try again Later", Toast.LENGTH_SHORT).show();
        }else if(s!=null) {
            done.getResult(s);
        }
        pd.dismiss();
    }
}
