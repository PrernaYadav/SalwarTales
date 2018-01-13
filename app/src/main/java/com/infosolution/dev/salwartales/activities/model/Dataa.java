package com.infosolution.dev.salwartales.activities.model;

/**
 * Created by amit on 1/11/2018.
 */

public class Dataa {


    public int image;
    public String name;

    public String value;

    public Dataa(int image, String name, String value) {
        this.image = image;
        this.name = name;
        this.value = value;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
