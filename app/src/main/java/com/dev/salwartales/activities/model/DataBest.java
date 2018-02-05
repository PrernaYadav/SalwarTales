package com.dev.salwartales.activities.model;

/**
 * Created by amit on 1/25/2018.
 */

public class DataBest {

    public String image;
    public String name;

    public String getProid() {
        return Proid;
    }

    public void setProid(String proid) {
        Proid = proid;
    }

    String Proid;

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

    public DataBest(String qtyLeft, int favimage) {
        QtyLeft = qtyLeft;
        this.favimage = favimage;
    }

    String QtyLeft;
    int favimage;

    public DataBest(String favStatus) {
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

    public DataBest() {
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
