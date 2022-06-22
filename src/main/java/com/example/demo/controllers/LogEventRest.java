package com.example.demo.controllers;


import com.example.demo.basicModels.logEvent.LogEvent;
import com.example.demo.repos.LogEventRepo;
import org.apache.juli.logging.Log;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class LogEventRest {

    @Resource
    LogEventRepo logEventRepo;

    @RequestMapping("/get-all-log-events")
    public List<LogEvent> getAllEventsInLog() throws IOException {
        return logEventRepo.findAll(Sort.by(Sort.Order.asc("date")));
    }
}
