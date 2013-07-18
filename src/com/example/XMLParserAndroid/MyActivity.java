package com.example.XMLParserAndroid;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;
import com.example.XMLParserAndroid.XMLAdapter.XMLAdapter;
import com.example.XMLParserAndroid.XMLModel.XMLModel;
import com.example.XMLParserAndroid.XMLObject.XMLObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyActivity extends ListActivity {

    private XMLObject xmlObject;
    private XMLAdapter adapter;
    private ArrayList<HashMap<String, String>> xmlParsedList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        if (isInternetConnected()) {
            LoadingProgressDialog loadingProgressDialog = new LoadingProgressDialog(this);
            loadingProgressDialog.execute();
        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null;
    }

    private class LoadingProgressDialog extends AsyncTask<Void, Void, Void> {

        private Activity innerActivity;
        private ProgressDialog progressDialog;

        public LoadingProgressDialog(Activity innerActivity) {
            this.innerActivity = innerActivity;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(innerActivity);
            progressDialog.setMessage("Loading...");
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            xmlObject = new XMLObject();
            try {
                xmlParsedList = xmlObject.readXml(XMLModel.XML_URL);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (progressDialog.isShowing() && progressDialog != null) {
                progressDialog.dismiss();
            }
            adapter = new XMLAdapter(innerActivity, xmlParsedList);
            setListAdapter(adapter);
        }
    }
}
