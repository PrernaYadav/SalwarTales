package com.dev.salwartales.activities.model;

/**
 * Created by amit on 1/11/2018.
 */

public class Dataa {


    public String image;
    public String name;

    public String getHoriPorId() {
        return HoriPorId;
    }

    public void setHoriPorId(String horiPorId) {
        HoriPorId = horiPorId;
    }

    String HoriPorId;

    public String getQtyLeft() {
        return QtyLeft;
    }

    public void setQtyLeft(String qtyLeft) {
        QtyLeft = qtyLeft;
    }

    public int getFavimage() {
        return favimage;
    }

    public void setFavimage(int favimage) {
        this.favimage = favimage;
    }

    public Dataa(String qtyLeft, int favimage) {
        QtyLeft = qtyLeft;
        this.favimage = favimage;
    }

    String QtyLeft;
    int favimage;

    public Dataa(String favStatus) {
        FavStatus = favStatus;
    }

    public String getFavStatus() {
        return FavStatus;
    }

    public void setFavStatus(String favStatus) {
        FavStatus = favStatus;
    }

    String FavStatus;
    public String value;

    public Dataa() {
        this.image = image;
        this.name = name;
        this.value = value;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
