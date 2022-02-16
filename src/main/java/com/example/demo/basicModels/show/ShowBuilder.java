package com.example.demo.basicModels.show;

import com.example.demo.basicModels.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShowBuilder {

    public String title;

    public List<DateTime> showDates = new ArrayList<>();

    public List<DateTime> rehearsalDates = new ArrayList<>();

    public int numberOfServices;

    public String notes;

    public ShowBuilder() {
    }

    public ShowBuilder title(String title) {
        Optional<String> titleCheck = Optional.ofNullable(title);
        titleCheck.ifPresent(gottenTitle -> this.title = gottenTitle);
        return this;
    }

    public ShowBuilder withDate(DateTime primaryDate) {
        if (primaryDate != null) {
            showDates.add(primaryDate);
        }
        return this;
    }

    public ShowBuilder showDates(List<DateTime> showDates) {
        Optional<List<DateTime>> datesOpt = Optional.ofNullable(showDates);
        datesOpt.ifPresent(gotten -> this.showDates = gotten);
        return this;
    }

    public ShowBuilder rehearsalDates(List<DateTime> rehearsalDates) {
        Optional<List<DateTime>> datesOpt = Optional.ofNullable(rehearsalDates);
        datesOpt.ifPresent(gotten -> this.rehearsalDates = gotten);
        return this;
    }

    public ShowBuilder numberOfServices(int numberOfServices) {
        if (numberOfServices > 0) {
            this.numberOfServices = numberOfServices;
        }
        return this;
    }

    public ShowBuilder notes(String notes) {
        Optional<String> notesOpt = Optional.ofNullable(notes);
        notesOpt.ifPresent(gotten -> this.notes = gotten);
        return this;
    }


    public Show build() {
        return new Show(this);
    }
}
