package com.example.demo.controllers;


import com.example.demo.legos.PlayerInChair;
import com.example.demo.legos.ShowPiece;
import com.example.demo.repos.PlayerInChairRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;

@CrossOrigin
@RestController
public class PICRest {

    @Resource
    PlayerInChairRepo picRepo;





}
