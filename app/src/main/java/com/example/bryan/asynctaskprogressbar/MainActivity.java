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
//        new ProgressShow().execute();
        String url = "https://www.google.com/search?q=nguoi+toi+yeu&client=ubuntu&hs=pO5&channel=" +
                "fs&source=lnms&tbm=isch&sa=X&ved=0ahUKEwjX_4ns7ubZAhVFGJQKHXc9AngQ_AUICygC&biw=" +
                "1299&bih=639#imgrc=VDngfuht2b6ElM:";
//        new AsyncTaskDownloadImage().execute(url);
        new DownloadImage(this, url).execute();

    }

    //Async Task
//    private class ProgressShow extends AsyncTask<Void,Integer,Void>{
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            mtvTextDes.append("Begin connection to Server... \n");
//        }
//
//        @Override
//        protected Void doInBackground(Void... voids) {
//            mtvTextDes.append("... ... ... ...  \n");
//            mtvTextDes.append("... ... ... ...  \n");
//            mtvTextDes.append("... ... ... ...  \n");
//            mtvTextDes.append("... ... ... ...  \n");
//            mtvTextDes.append("Hacking server... \n");
//            int c=0;
//            for(int i=0;i<=100;i++){
//                SystemClock.sleep(500);
//                ++c;
//                publishProgress(i,c);
//
//            }
//            return null;
//
//        }
//
//        @Override
//        protected void onPostExecute(Void aVoid) {
//            mtvTextDes.append("Done\n");
//            mtvTextShow.setText("Complete Mission");
//            super.onPostExecute(aVoid);
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            mpbHoriProgess.setProgress(values[0]);
//            mbpProgressDowloading.setProgress(values[1]);
//            mtvTextShow.append("Coping file xxx.dgr.dgr... \n");
//        }
//    }

//    public class AsyncTaskDownloadImage extends AsyncTask<String, Integer, Bitmap> {
//
//
//        protected Bitmap doInBackground(String... urls) {
//            String urldisplay = urls[0];
//            Bitmap mIcon11 = null;
//            try {
//                InputStream in = new java.net.URL(urldisplay).openStream();
//                mIcon11 = BitmapFactory.decodeStream(in);
//            } catch (Exception e) {
//                Log.d("Error", e.getStackTrace().toString());
//
//            }
//            return mIcon11;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//            mbpProgressDowloading.setProgress(values[0]);
//        }
//
//        protected void onPostExecute(Bitmap result) {
//            mtvTextDes.setText("Download Done");
//            mivShow.setImageBitmap(result);
//
//        }
//    }

    private class DownloadImage extends AsyncTask<Void, Integer, Integer> {

        Context mContext;
        String url;


        public DownloadImage(Context mContext, String url) {
            this.mContext = mContext;
            this.url = url;
        }


        @Override
        protected Integer doInBackground(Void... voids) {
            // Request Post
            RequestQueue requestQueue = new Volley().newRequestQueue(mContext);
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            mtvTextDes.append(response + "\n");
                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
            //
            RequestQueue rq = Volley.newRequestQueue(mContext);
            StringRequest postReq = new StringRequest(Request.Method.POST, url, null, null) {

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    String param = url;
                    params.put("param1", param);
                    return params;
                }

            };
            rq.add(postReq);


            // download binary bitmap
            ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap response) {
                    mivShow.setImageBitmap(response);
                }
            }, 0, 0, null, null);


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
