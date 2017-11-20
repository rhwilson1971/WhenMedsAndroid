package com.wymsii.whenmeds.script;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by reuben on 11/19/17.
 */

public final class ScriptHelper {
    public static Script parseString(String data){

        Script script = new Script();
        Boolean success = false;

        try {

            script.setRawData(data);
            // this.rawData = data;
            JSONObject object = (JSONObject) new JSONTokener(data).nextValue();

            JSONArray results = object.getJSONArray("results");

            for ( int i = 0; i<results.length(); i++){
                JSONObject result = results.getJSONObject(i);
                JSONObject patient = result.getJSONObject("patient");
                JSONArray drugs =
                        patient.getJSONArray("drug");

                for( int j=0; j<drugs.length(); j++){
                    JSONObject drug = drugs.getJSONObject(j);

                    if(drug.has("openfda")) {

                        JSONObject openFda = drug.getJSONObject("openfda");

                        JSONArray genericNames = openFda.getJSONArray("generic_name");
                        JSONArray brandNames = openFda.getJSONArray("brand_name");

                        int x=0;
                        for( ; x<genericNames.length(); x++){

                            script.genericTitles.add(genericNames.getString(x));
                        }
                        x=0;
                        for( ; x<brandNames.length(); x++){
                            script.brandTitles.add(brandNames.getString(x));
                        }
                    }
                }
                Log.i("INFO", "Number of drugs is " + drugs.length());
            }

            Log.i("INFO", "");
            success = true;
        }
        catch (JSONException ex){
            Log.e("ERROR", ex.getMessage());
        }

        return script;
    }
}
