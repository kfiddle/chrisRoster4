package com.example.demo.controllers;


import com.example.demo.basicModels.show.Horloge;
import com.example.demo.basicModels.show.HorlogeBuilder;
import com.example.demo.basicModels.show.Show;
import com.example.demo.enums.Event;
import com.example.demo.repos.HorlogeRepo;
import com.example.demo.repos.ShowRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@CrossOrigin
@RestController
public class HorlogeRest {

    @Resource
    HorlogeRepo horlogeRepo;

    @Resource
    ShowRepo showRepo;


    @RequestMapping("/get-all-primary-dates")
    public List<Horloge> getAllPrimaryDateHorlogesInDatabase() {
        List<Horloge> primaryDates = horlogeRepo.findByEvent(Event.PRIMARYDATE);
        Collections.sort(primaryDates);
        return primaryDates;
    }


    @RequestMapping("/get-performances-by-primary-date")
    public List<Show> getAllShowsByPrimaryDate() {

        try {
            List<Show> showsToReturn = new ArrayList<>();

            List<Horloge> primaryDates = horlogeRepo.findByEvent(Event.PRIMARYDATE);
            Collections.sort(primaryDates);

            for (Horloge horloge : primaryDates) {
                showsToReturn.add(horloge.getShow());
            }

            for (Show show : showRepo.findAll()) {
                if (!showsToReturn.contains(show)) {
                    showsToReturn.add(show);
                }
            }
            return showsToReturn;

        } catch (
                Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    @PostMapping("/add-horloge")
    public Horloge addHorloge(@RequestBody Horloge incoming) throws IOException {

        Horloge newOne = new HorlogeBuilder()
                .date(incoming.getDate())
                .show(incoming.getShow())
                .event(incoming.getEvent())
                .startTime(incoming.getStartTime())
                .endTime(incoming.getEndTime())
                .build();
        horlogeRepo.save(newOne);

        System.out.println(newOne.getDate());
        System.out.println(newOne.getEvent());
        System.out.println(newOne.getShow().getTitle());
        System.out.println(newOne.getStartTime());
        System.out.println(newOne.getEndTime());
        return newOne;
    }

    @PostMapping("/get-date-from-show")
    public LocalDate getPrimaryDateFromShow(@RequestBody Show show) {
        try {
            Optional<Show> showToFind = showRepo.findById(show.getId());
            if (showToFind.isPresent()) {
                Show foundShow = showToFind.get();
                if (horlogeRepo.existsByEventAndShow(Event.PRIMARYDATE, foundShow)) {
                    return horlogeRepo.findByEventAndShow(Event.PRIMARYDATE, foundShow).getDate();
                }
            }
        } catch (
                Exception error) {
            error.printStackTrace();

        }
        return null;
    }

}
