package com.wymsii.whenmeds;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.wymsii.whenmeds.models.Script;

public class AddScriptActivity extends AppCompatActivity {

    static final String API_KEY = "TJyN8tyOyaLAuuogQl8SQVft3bmgcExJUNnbEPGU";
    static final String API_URL = "https://api.fda.gov/drug/event.json?api_key=%s&search:drug.brand_name=%s";

    ListView drugListView;
    ProgressBar progressBar;
    TextView responseView;
    EditText drugName;

    List<String> drugItemsList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter; // = new ArrayAdapter<String>()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_script);

        this.drugName = (EditText)findViewById(R.id.drugName);
        this.progressBar = (ProgressBar)findViewById(R.id.progressBar3);
         //  this.responseView = (TextView) findViewById(R.id.responseView);
        this.drugListView = (ListView)findViewById(R.id.drugSelections);



        this.arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                drugItemsList );

        drugListView.setAdapter(arrayAdapter);

        // arrayAdapter.add("The jones");

        Button searchButton = (Button)findViewById(R.id.button);

        searchButton.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View v){
                // clear the listView
                ListView lv = (ListView)findViewById(R.id.drugSelections);
                arrayAdapter.clear();
                lv.invalidateViews();

                // Hide virtual keyboard
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);

                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                new FindDrugTask().execute();
            }
        });

        // hide progress bar
        progressBar.setVisibility(View.GONE);
    }

    /* called when the user taps the Send button */
    //public void search(View view){


    //}

    class FindDrugTask extends AsyncTask<Void,Void,String> {

        private Exception exception;

        protected void onPreExecute(){
            progressBar.setVisibility(View.VISIBLE);
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
                    Log.e("INFO", "read site data");
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();

                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();

                    Log.e("INFO", "data " + line);
                    return stringBuilder.toString();

                }
                finally{
                    urlConnection.disconnect();
                }

            }
            catch(Exception e){
                Log.e("ERROR", e.getMessage());
            }
            Log.e("INFO", "done");
            return null;

        }

        protected void onPostExecute(String response)
        {
            if(response == null) {
                arrayAdapter.add("Couldn't locate drug information");
            }
            else{
                Script script = new Script();
                Boolean good = script.parseString(response);

                if(good){
                    for (String item : script.getBrandNames()) {
                        arrayAdapter.add(item);
                    }

                    for(String item : script.getGenericNames()){
                        arrayAdapter.add(item);
                    }
                }

                arrayAdapter.notifyDataSetChanged();

                ListView lv = (ListView)findViewById(R.id.drugSelections);
                lv.invalidateViews();
            }

            progressBar.setVisibility(View.GONE);
            // responseView.setText(responseText.toString());
        }
    }
}
