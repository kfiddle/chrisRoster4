package com.example.demo.repos;

import com.example.demo.basicModels.logEvent.LogEvent;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LogEventRepo extends CrudRepository<LogEvent, Long> {

    List<LogEvent> findAll(Sort sort);
}
