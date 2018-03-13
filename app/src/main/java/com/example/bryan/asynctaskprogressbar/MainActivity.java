package com.example.bryan.asynctaskprogressbar;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiView();
        String url = "https://i.ytimg.com/vi/8tg2SL36PWk/maxresdefault.jpg";
        new DownloadImage(this, url).execute();
    }

    private class DownloadImage extends AsyncTask<Void, Integer, Integer> {
        Context mContext;
        String url;

        public DownloadImage(Context mContext, String url) {
            this.mContext = mContext;
            this.url = url;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            RequestQueue rq = Volley.newRequestQueue(mContext);
            ImageRequest request = new ImageRequest(url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            mivShow.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            Log.i("", "onErrorResponse: ");
                        }
                    });

            rq.add(request);
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mtvTextDes.setText("done");
        }
    }

    private void intiView() {
        mpbHoriProgess = findViewById(R.id.horizontal_progessbar);
        mbpCircleProgress = findViewById(R.id.circle_progessbar);
        mbpProgressDowloading = findViewById(R.id.progressbar_dowloading);
        mtvTextDes = findViewById(R.id.text_decestion);
//        mtvTextShow = findViewById(R.id.text_show);
        mivShow = findViewById(R.id.image_show);

    }

    //region VARS
    private ProgressDialog pDialog;
    private ProgressBar mpbHoriProgess;
    private ProgressBar mbpProgressDowloading;
    private ProgressBar mbpCircleProgress;
    private TextView mtvTextDes;
    //    private TextView mtvTextShow;
    private ImageView mivShow;
    //endregion

}
