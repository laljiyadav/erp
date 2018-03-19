package main.erp.root.erp.main.erp.root.erp.execute;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import main.erp.root.erp.main.erp.root.erp.activity.StudentActivity;
import main.erp.root.erp.main.erp.root.erp.interfaces.LoginDone;


public class studentExecute extends AsyncTask<String,String,String> {


    LoginDone done;
    String urlPath;
    ProgressDialog pd;
    Context c;

    public studentExecute(StudentActivity loginActivity, String url) {

        done=(LoginDone)loginActivity;
        urlPath=url;
        Log.e("URL", urlPath);
        c=loginActivity;
    }

    public studentExecute(FragmentActivity activity, String url) {

        done=(LoginDone)activity;
        urlPath=url;
        c=activity;
    }
    public studentExecute()
    {

    }



    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(c);
        pd.setMessage("loading");
        pd.show();
    }

    @Override
    protected String doInBackground(String... params) {
   /*     try {
            Log.d("register", "doInBackground" + urlPath);
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(urlPath);
            //    httppost.setEntity(new UrlEncodedFormEntity(nameValuePhoneNumber));
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity ent = resp.getEntity();
            String getResult = EntityUtils.toString(ent);
            return getResult;
        }
        catch (Exception e){
            return null;
        }*/

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
            return response.toString();

        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {

        Log.d("result execute"," sssssssss"+ s);
            done.getResult(s);


        pd.dismiss();
    }
}
