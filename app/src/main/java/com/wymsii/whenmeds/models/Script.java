package com.wymsii.whenmeds.models;

/**
 * Created by Reuben Wilson on 10/5/2017.
 */

public class Script {

    private String description;
    private String dosage;
    private int refills;
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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



}
