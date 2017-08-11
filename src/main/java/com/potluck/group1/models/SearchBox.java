package com.potluck.group1.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SearchBox {

    // @Id is necessary even if SearchBox is not being used in the db
    @Id
    private long id;
    private String searchEntry;


    public String getSearchEntry() {
        return searchEntry;
    }

    public void setSearchEntry(String searchEntry) {
        this.searchEntry = searchEntry;
    }


}
