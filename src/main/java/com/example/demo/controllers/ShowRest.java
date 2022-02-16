package com.example.demo.controllers;


import com.example.demo.basicModels.player.Player;
import com.example.demo.basicModels.player.PlayerEditor;
import com.example.demo.basicModels.show.Show;
import com.example.demo.basicModels.show.ShowBuilder;
import com.example.demo.basicModels.show.ShowEditor;
import com.example.demo.repos.ShowRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
public class ShowRest {

    @Resource
    ShowRepo showRepo;

    @RequestMapping("/get-all-performances")
    public Collection<Show> getAllShows() {
        List<Show> sortedShows = new ArrayList<>((Collection<Show>) showRepo.findAll());

        try {
            Collections.sort(sortedShows);
            return sortedShows;

        } catch (Exception error) {
            error.printStackTrace();
        }

        return (Collection<Show>) showRepo.findAll();
    }

    @PostMapping("/add-performance")
    public Collection<Show> addAShow(@RequestBody Show incoming) throws IOException {

        try {
            if (!showRepo.existsByTitle(incoming.getTitle())) {
                showRepo.save(new ShowBuilder()
                        .title(incoming.getTitle())
                        .performanceDates(incoming.getPerformanceDates())
                        .rehearsalDates(incoming.getRehearsalDates())
                        .numberOfServices(incoming.getNumberOfServices())
                        .notes(incoming.getNotes())
                        .build());
            }
        } catch (
                Exception error) {
            error.printStackTrace();
        }

        return (Collection<Show>) showRepo.findAll();
    }

    @PostMapping("/edit-performance")
    public Collection<Show> editAShowInDatabase(@RequestBody Show incoming) throws IOException {
        try {
            Optional<Show> showToFind = showRepo.findById(incoming.getId());
            if (showToFind.isPresent()) {
                Show showToEdit = showToFind.get();
                ShowEditor editor = new ShowEditor(showToEdit);
                editor.editFrom(incoming);
            }
            return (Collection<Show>) showRepo.findAll();

        } catch (Exception error) {
            error.printStackTrace();
        }
        return (Collection<Show>) showRepo.findAll();
    }

}
