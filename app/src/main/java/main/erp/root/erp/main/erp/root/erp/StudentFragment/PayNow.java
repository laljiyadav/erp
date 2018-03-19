package main.erp.root.erp.main.erp.root.erp.StudentFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import main.erp.root.erp.R;


public class PayNow extends Fragment {

private WebView webView;


    public PayNow() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View layout= inflater.inflate(R.layout.fragment_pay_now, container, false);
        webView = (WebView) layout.findViewById(R.id.web_view);
       webView.loadUrl("https://www.payumoney.com/");
        return layout;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }





}
