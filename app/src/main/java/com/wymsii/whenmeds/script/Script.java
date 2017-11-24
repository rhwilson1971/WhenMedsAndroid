package com.wymsii.whenmeds.script;

import com.wymsii.whenmeds.reminder.Reminder;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Reuben Wilson on 10/5/2017.
 */

@Entity(tableName = "scripts")
public class Script {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String description;
    private String dosage;
    private int refills;

    private String genericName;
    private String brandName;

    @Ignore
    private String rawData;
    @Ignore
    Set<String> genericTitles = new HashSet<>();
    @Ignore
    Set<String> brandTitles = new HashSet<>();


    public void setRawData(String rawData) { this.rawData = rawData;}

    public int getId() {
        return this.id;
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

    public String getGenericName(){
        return this.genericName;
    }
    public void setGenericName(String genericName) {
        this.genericName = genericName;
    }

    public String getBrandName() { return this.brandName; }
    public void setBrandName(String brandName) { this.brandName = brandName; }

    public String getRawData(){
        return this.rawData;
    }
    public Boolean parseString(String json){
        Boolean success = false;
        try {

            this.rawData = json;
            JSONObject object = (JSONObject) new JSONTokener(this.rawData).nextValue();

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

                            genericTitles.add(genericNames.getString(x));
                        }
                        x=0;
                        for( ; x<brandNames.length(); x++){
                            brandTitles.add(brandNames.getString(x));
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

        return success;
    }

    public Set<String> getBrandNames() { return this.brandTitles; }

    public Set<String> getGenericNames() { return this.genericTitles; }
}
