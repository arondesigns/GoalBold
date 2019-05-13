package com.example.rishikesh.goalbold;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import java.util.Map;

/**
 * Created by Rishikesh on 19-07-2017.
 */

public class Home_list_content extends AppCompatActivity {
    TextView title;
    WebView content;
    ProgressDialog progressDialog;
    Gson gson;
    Map<String, Object> mapPost;
    Map<String, Object> mapTitle;
    Map<String, Object> mapContent;
    private static final String TAG = "rishikesh";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_content);
        final String id = getIntent().getExtras().getString("id");
        
        title = (TextView) findViewById(R.id.title);
        content = (WebView)findViewById(R.id.content);
        progressDialog = new ProgressDialog(Home_list_content.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        Log.d(TAG, "before url");
        String url = "http://www.goalbold.com/wp-json/wp/v2/posts/"+id+"?fields=title,content";


        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override

            public void onResponse(String s) {
                Log.d(TAG, "befor gson");
                gson = new Gson();
                mapPost = (Map<String, Object>) gson.fromJson(s, Map.class);
                mapTitle = (Map<String, Object>) mapPost.get("title");
                mapContent = (Map<String, Object>) mapPost.get("content");
                title.setText(mapTitle.get("rendered").toString());
                content.loadData(mapContent.get("rendered").toString(),"text/html","UTF-8");
                progressDialog.dismiss();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressDialog.dismiss();
                Toast.makeText(Home_list_content.this, id, Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Home_list_content.this);
        rQueue.add(request);
    }
}
