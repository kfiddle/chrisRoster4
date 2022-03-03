package com.example.demo.repos;

import com.example.demo.basicModels.show.Horloge;
import com.example.demo.enums.Event;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface HorlogeRepo extends CrudRepository<Horloge, Long> {

    ArrayList<Horloge> findByEvent(Event primarydate);
}
