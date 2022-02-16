package com.example.demo.repos;

import com.example.demo.basicModels.show.Show;
import org.springframework.data.repository.CrudRepository;

public interface ShowRepo extends CrudRepository<Show, Long> {

    boolean existsByTitle(String title);
}
