package com.example.dictionaryapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

public class DictionaryRequest extends AsyncTask<String, Integer, String> {

    Context context;
    TextView showDef;

    DictionaryRequest(Context context, TextView tv){
        this.context = context;
        showDef = tv;
    }

    @Override
    protected String doInBackground(String... params) {

        final String app_id = "2d47d39d";
        final String app_key = "8002a226e11909946e0d2502f016bf40";

        try {
            URL url = new URL(params[0]);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", app_id);
            urlConnection.setRequestProperty("app_key", app_key);

            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        String def;
        try {
            JSONObject js = new JSONObject(s);
            JSONArray results = js.getJSONArray("results");

            JSONObject lEntries = results.getJSONObject(0);
            JSONArray lArray = lEntries.getJSONArray("lexicalEntries");

            JSONObject entries = lArray.getJSONObject(0);
            JSONArray e = entries.getJSONArray("entries");

            JSONObject jsonObject = e.getJSONObject(0);
            JSONArray sensesArray = jsonObject.getJSONArray("senses");

            JSONObject de = sensesArray.getJSONObject(0);
            JSONArray d = de.getJSONArray("definitions");

            def = d.getString(0);
            showDef.setText(def + ".");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v("Result of Dictionary", "onPostExecute" + s);
    }
}
