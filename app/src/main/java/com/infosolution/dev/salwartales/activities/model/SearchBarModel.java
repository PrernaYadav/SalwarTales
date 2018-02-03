package com.infosolution.dev.salwartales.activities.model;

/**
 * Created by amit on 2/3/2018.
 */

public class SearchBarModel {
    public SearchBarModel(String itemNameSearch) {

        this.ItemNameSearch=itemNameSearch;

    }

    public String getItemNameSearch() {
        return ItemNameSearch;
    }

    public void setItemNameSearch(String itemNameSearch) {
        ItemNameSearch = itemNameSearch;
    }

    String ItemNameSearch;
}
