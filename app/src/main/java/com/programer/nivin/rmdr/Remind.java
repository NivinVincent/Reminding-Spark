package com.programer.nivin.rmdr;

import android.util.Log;

/**
 * Created by Nivin Vincent on 9/23/2017.
 */
public class Remind {
    String name,category,certificate,date;
    int id;
    public Remind() {
    }
    public Remind(int id1,String name1,String category1,String certificate1,String date1) {
        this.id=id1;
        this.name=name1;
        this.category=category1;
        this.certificate=certificate1;
        this.date=date1;
    }
    public Remind(String name1,String category1,String certificate1,String date1) {

        this.name=name1;
        this.category=category1;
        this.certificate=certificate1;
        this.date=date1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
