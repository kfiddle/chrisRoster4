package com.example.demo.basicModels.show;


import com.example.demo.basicModels.DateTime;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Show {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private int numberOfServices;

    private String notes;

    public Show() {
    }

    public Show(ShowBuilder showBuilder) {
        this.title = showBuilder.title;
        this.numberOfServices = showBuilder.numberOfServices;
        this.notes = showBuilder.notes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumberOfServices(int numberOfServices) {
        this.numberOfServices = numberOfServices;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfServices() {
        return numberOfServices;
    }

    public String getNotes() {
        return notes;
    }



}
