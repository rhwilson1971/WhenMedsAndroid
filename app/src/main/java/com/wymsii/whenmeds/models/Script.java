package com.wymsii.whenmeds.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Created by Reuben Wilson on 10/5/2017.
 */

//@Entity
public class Script {

    private String description;
    private String dosage;
    private int refills;
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public int getRefills() {
        return refills;
    }

    public void setRefills(int refills) {
        this.refills = refills;
    }

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void parseString(String json){

        try {


            JSONObject object = (JSONObject) new JSONTokener(json).nextValue();




        }
        catch (JSONException ex){
            Log.e("ERROR", ex.getMessage());
        }

        //try {
//                JSONObject object = (JSONObject) new JSONTokener(response).nextValue();
//                String requestID = object.getString("requestId");
//                int likelihood = object.getInt("likelihood");
//                JSONArray photos = object.getJSONArray("photos");
//                .
//                .
//                .
//                .
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
    }

}
