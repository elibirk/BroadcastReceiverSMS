package com.example.spec.broadcastreceiversms;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView;

public class MainActivity extends AppCompatActivity {

    private WebView webview; //webview to hold the website
    ListView list; //list for the URLs
    private final String[] websites = {"https://www.goodreads.com/", "http://www.bookfinder.com/",
            "http://www.barnesandnoble.com/mobile/", ""}; //website URLs, plus one blank for our SMS

    ArrayAdapter<String> adapter; //adapter to use for the ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle bundle = getIntent().getExtras();
        if (getIntent().hasExtra("SMS")) {//if there was an SMS, grab the URL
            String sms = bundle.getString("SMS");
           if(!sms.startsWith("https://") && !sms.startsWith("http://")){
               sms = "https://" + sms; //appends https:// if it isn't there
           }
            websites[3] = sms; //adds the SMS to the list of URLs
        }//end if hasExtra


        webview = (WebView) findViewById(R.id.webview);//access our webview
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient()); //to handle URLs
        webview.loadUrl(websites[3]); //defaults to blank or inputted URL

        list = (ListView) findViewById(R.id.listview);
        //accesses our ListView


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, websites);
        //makes a simple adapter for a normal list
        list.setAdapter(adapter); //sets the adapter to use the URLs in our listview


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                webview.loadUrl(websites[position]);
                //load the chosen website into the WebView
            }
        });//end setItemClickListener


    }//end onCreate
}//end MainActivity
