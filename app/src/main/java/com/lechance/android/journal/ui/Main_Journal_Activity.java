package com.lechance.android.journal.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lechance.android.journal.R;
import com.lechance.android.journal.ui.activity.TestActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class was Created by lechance on 20 May 2016 at 4:48 AM.
 */
public class Main_Journal_Activity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    @BindView(R.id.btn_async)
    private Button btnAsync;
    @BindView(R.id.frame_layout)
    private FrameLayout frameLayout;

    @BindView(R.id.frame_content)
    private TextView content;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_journal_activity);

        ButterKnife.bind(Main_Journal_Activity.this);

        content = (TextView) findViewById(R.id.frame_content);
        if (content != null) {
            content.setText("original");
        }
        btnAsync = (Button) findViewById(R.id.btn_async);
        btnAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImg();
            }
        });

        //fragment

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return ;
        }



        //btn_test_af

        Button test_af = (Button) findViewById(R.id.btn_lief_cycle);
        if (test_af != null) {
            test_af.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Main_Journal_Activity.this, TestActivity.class));
                }
            });
        }
    }

    private void downloadImg() {

        try {
            HttpURLConnection d;
            URL url = new URL("http://api.icndb.com/jokes/random");
            new downloadTask(this).execute(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {


        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(getApplicationContext(), "Please click again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    public void liefCycle(View view) {
        startActivity(new Intent(Main_Journal_Activity.this, TestActivity.class));
    }

    public static class CusFragment extends Fragment {


        public CusFragment() {
            super();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            LinearLayout layout = new LinearLayout(getContext());
            layout.addView(inflater.inflate(R.layout.journal_listview_item, null));
//            return super.onCreateView(inflater, container, savedInstanceState);
            return layout;
        }
    }

    private final class downloadTask extends AsyncTask<URL, Integer, String> {

        int hasRead = 0;
        private Context context;
        private URLConnection connection;
        private ProgressDialog dialog;

        public downloadTask(Context context) {
            super();
            this.context = context;
        }

        @Override
        protected String doInBackground(URL... params) {
            try {
                ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
                connection = params[0].openConnection();
                connection.setConnectTimeout(3000);
                URL url = connection.getURL();
                Log.i("journal_doInBackground", url.toString());
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line).append("\n");
                    hasRead++;
                    publishProgress(hasRead);
                }

                return builder.toString();
            } catch (IOException e) {
//                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Not network", Toast.LENGTH_SHORT).show();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context);
            dialog.setTitle("Downloading");
            dialog.setMessage("from api.icndb.com");
            dialog.setCancelable(true);
            dialog.setIndeterminate(true);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(context, s, Toast.LENGTH_LONG).show();
            dialog.dismiss();

            try {
                JSONObject data = new JSONObject(s);

                JSONObject value = data.getJSONObject("value");
                String joke = value.getString("joke");
                Log.i("lechance", "joke: " + joke);
                content.setText(joke);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            dialog.setMessage("completed ...");
            dialog.setProgress(values[0]);
        }
    }
}
