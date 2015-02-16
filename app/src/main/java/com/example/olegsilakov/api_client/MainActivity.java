package com.example.olegsilakov.api_client;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity {
//    WebView content_view;
    TextView content_text;
    URLImageParser p;
    
    public String url = "posts/347437";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        URLImageParser.setContext(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        content_view = (WebView) findViewById(R.id.content_view);
        content_text = (TextView) findViewById(R.id.content_text);
        p = new URLImageParser(content_text, this);
        new getPost().execute("");
//        HashMap<>
//        try {
//            clouds = (List<FileItem>) jsonHttpClient.GetObject(result_url, FileItem.class);
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class getPost extends AsyncTask<String, String, String> {

        @Override
        protected void onPostExecute(String res){
//            content_view.loadData(res, "text/html", null);
//            content_text.setText(Html.fromHtml(res));
            Spanned htmlSpan = Html.fromHtml(res, p, null);
            content_text.setText(htmlSpan);
        }

        @Override
        protected String doInBackground(String... params) {
            JSONHTTPClient jsonHttpClient = new JSONHTTPClient();
            String post = "";
            try {
                post = jsonHttpClient.GetObject(url);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return post;
        }
    }
}
