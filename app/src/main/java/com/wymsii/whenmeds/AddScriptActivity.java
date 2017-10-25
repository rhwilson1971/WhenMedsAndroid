package com.wymsii.whenmeds;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddScriptActivity extends AppCompatActivity {

    static final String API_KEY = "TJyN8tyOyaLAuuogQl8SQVft3bmgcExJUNnbEPGU";
    static final String API_URL = "https://api.fda.gov/drug/event.json?api_key=%s&search:drug.brand_name=%s";

    ProgressBar progressBar;
    TextView responseView;
    EditText drugName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_script);

        this.drugName = (EditText)findViewById(R.id.drugName);
        this.progressBar = (ProgressBar)findViewById(R.id.progressBar3);
        this.responseView = (TextView) findViewById(R.id.responseView);

        Button searchButton = (Button)findViewById(R.id.button);

        searchButton.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                new FindDrugTask().execute();
            }
        });

    }

    /* called when the user taps the Send button */
    //public void search(View view){


    //}
    class FindDrugTask extends AsyncTask<Void,Void,String> {

        private Exception exception;

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
            responseView.setText("");
        }

        protected String doInBackground(Void... urls){
            String drug = drugName.getText().toString();
            Log.i("INFO", drug);

            try {
                // (patient.drug.medicinalproduct:cetirizine+loratadine+diphenhydramine)
                //https://api.fda.gov/drug/event.json?search=(patient.drug.openfda.brand_name:naprosyn)

                // String urlData = String.format(API_URL, API_KEY, drug);
                String urlData = String.format("https://api.fda.gov/drug/event.json?search=(patient.drug.openfda.brand_name:%s)", drug);
                Log.i("INFO", urlData);
                URL url = new URL(urlData);
                HttpURLConnection urlConnection =
                        (HttpURLConnection)url.openConnection();


                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();

                    String line;
                    while((line = bufferedReader.readLine()) != null);

                    stringBuilder.append(line).append("\n");
                    bufferedReader.close();
                    return stringBuilder.toString();

                }
                finally{
                    urlConnection.disconnect();
                }

            }
            catch(Exception e){
                Log.e("ERROR", e.getMessage());
            }

            return null;

        }

        protected void onPostExecute(String response)
        {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            progressBar.setVisibility(View.GONE);
            Log.i("INFO", response);
            responseView.setText(response);
        }

    }
}
