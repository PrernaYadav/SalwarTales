package com.infosolution.dev.salwartales.activities.model;

/**
 * Created by Shreyansh Srivastava on 1/16/2018.
 */

public class Cart {

    String Proname;
    int Proimage;
    String Proprice;

    public Cart(String proname, int proimage, String proprice) {
        Proname = proname;
        Proimage = proimage;
        Proprice = proprice;
    }

    public String getProname() {
        return Proname;
    }

    public void setProname(String proname) {
        Proname = proname;
    }

    public int getProimage() {
        return Proimage;
    }

    public void setProimage(int proimage) {
        Proimage = proimage;
    }

    public String getProprice() {
        return Proprice;
    }

    public void setProprice(String proprice) {
        Proprice = proprice;
    }
}
