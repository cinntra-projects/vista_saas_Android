package com.cinntra.vista.model;

import com.cinntra.vista.newapimodel.NewOpportunityRespose;

import java.util.List;

public class Company {
    String title;
    List<NewOpportunityRespose> items;
    public Company(String title, List<NewOpportunityRespose> items) {
        super();
        this.title = title;
        this.items = items;
    }

//    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

//    @Override
    public List<NewOpportunityRespose> getItems() {
        return items;
    }

    public void setItems(List<NewOpportunityRespose> items) {
        this.items = items;
    }
}
