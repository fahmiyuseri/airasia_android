package com.ipay88.airasia.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import com.ipay88.airasia.Constant;
import com.ipay88.airasia.R;
import org.json.JSONException;
import org.json.JSONObject;


public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;

    interface WebviewInterface {
        void geta();
    }

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment_page);
        //TextView tvtitle = (TextView) findViewById(R.id.strTitle);
        //tvtitle.setText("Payment");
        //  webviewInterFace = WebviewInterface(context, topupStatesChangeHandler);
        //String data = getIntent().getStringExtra("html");
        //String data = "{\"UserToken\":\"EZ4TV7UKEEF2SBDJ\",\"TopUpAmount\":\"10.00\",\"Currency\":\"MYR\",\"MsgDigest\":\"ef6d73c3078bc8a00b79a24bf124883d9a13b5fc218493fa752d07e468a860ec\",\"GeoLocation\":\"California\"}";
        JSONObject data = null;
        try {
            data = new JSONObject("{\"UserToken\":\"EZ4TV7UKEEF2SBDJ\",\"TopUpAmount\":\"10.00\",\"Currency\":\"MYR\",\"MsgDigest\":\"ef6d73c3078bc8a00b79a24bf124883d9a13b5fc218493fa752d07e468a860ec\",\"GeoLocation\":\"California\"}");
            data.put("TopUpAmount","10.00");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("sada",data.toString());
        webView = (WebView) findViewById(R.id.webview_payment);


        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        final JavaScriptInterface myJavaScriptInterface
                = new JavaScriptInterface(this);
        webView.addJavascriptInterface(myJavaScriptInterface, "IPAY88JS");
        webView.postUrl("https://wallet.ipay88.com/vas/portal/API/CusTopUpToken.aspx", data.toString().getBytes());

        //webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                         if (url.contains("privacy.asp")) {
                                             Intent intent = new Intent(WebViewActivity.this, WebViewActivity.class);
                                             intent.putExtra("url", url);
                                             startActivity(intent);
                                             return true;
                                         } else {
                                             return super.shouldOverrideUrlLoading(view, url);
                                         }

                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         Log.e("s", "Finished Loading URL: " + url);

                                         super.onPageFinished(view, url);

                                         if (url.endsWith("ConfirmationPage.asp")) {
                                             view.loadUrl("javascript:window.IPAY88JS.checkResult" +
                                                     "('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
                                             webView.setVisibility(View.INVISIBLE);

                                         } else if (url.contains("CusTopUpipay88RespToken.aspx")) {
                                             view.loadUrl("javascript:window.IPAY88JS.getNewBalance" +
                                                     "(document.getElementsByTagName('pre')[0].innerHTML);");
                                             webView.setVisibility(View.INVISIBLE);
                                         } else if (url.contains("CusTopUpToken.aspx")) {
                    view.loadUrl("javascript:window.IPAY88JS.getTopupToken" +
                            "(document.getElementsByTagName('pre')[0].innerHTML);");

                }

                                     }
                                 }
        );

        /*webView.loadData(data, "text/html", null);*/

        //   webView.postUrl("https://wallet.ipay88.com/vas/portal/API/CusTopUpToken.aspx",data.getBytes());
        new Handler(Looper.getMainLooper());
    }


    public class JavaScriptInterface {
        Context mContext;

        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface   // must be added for API 17 or higher
        public void checkResult(String toast) {
            JSONObject balance = null;
            try {
                balance = new JSONObject(toast);
                Intent returnIntent = new Intent();

                returnIntent.putExtra("status_code", balance.getString("Status_Code"));
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @JavascriptInterface   // must be added for API 17 or higher
        public void getNewBalance(String toast) {
            try {
                JSONObject balance = new JSONObject(toast);

                Intent returnIntent = new Intent();
                returnIntent.putExtra("status_code", balance.getString("Status_Code"));
                returnIntent.putExtra("result", balance.toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @JavascriptInterface   // must be added for API 17 or higher
        public void getTopupToken(String toast) {
            Log.d("sadaaa","sdsd");
            Log.d("sadaaa",toast);
            try {
                JSONObject data = new JSONObject(toast);

                String status = data.getString("Status_Code");
                if(
                        status.equals(Constant.TP_INVALID_USER)||
                        status.equals(Constant.TP_INVALID_REQ)||
                        status.equals(Constant.TP_INVALID_RANGE_FT_AMT)||
                        status.equals(Constant.TP_INVALID_MIN_RANGE_AMT)||
                        status.equals(Constant.TP_EXC_ACC_LIMIT_AMT)||
                        status.equals(Constant.TP_INVALID_RESP_GW)||
                        status.equals(Constant.TP_INVALID_MAX_RANGE_AMT)||
                        status.equals(Constant.TP_USER_NOT_ACTIVE)
                        ){

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("status_code", status);
                    returnIntent.putExtra("result", status);

                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
                else{
                    webView.setVisibility(View.VISIBLE);
                }




            } catch (JSONException e) {
                e.printStackTrace();

            }
        }

        @JavascriptInterface           // For API 17+
        public void closeClick() {
            finish();
        }
    }

    @Override
    public void onClick(View v) {
/*
        if (v.getId() == R.id.imgBack) {
            finish();
        }*/
    }
}
