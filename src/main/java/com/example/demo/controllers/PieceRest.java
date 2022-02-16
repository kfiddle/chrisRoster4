package com.example.demo.controllers;


import com.example.demo.basicModels.piece.Piece;
import com.example.demo.basicModels.piece.PieceBuilder;
import com.example.demo.repos.PieceRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;

@CrossOrigin
@RestController
public class PieceRest {

    @Resource
    PieceRepo pieceRepo;

    @RequestMapping("/get-all-pieces")
    public Collection<Piece> getAllPerformances() {
        return (Collection<Piece>) pieceRepo.findAll();
    }

    @PostMapping("/add-piece")
    public Collection<Piece> addPieceToDatabase(@RequestBody Piece incoming) throws IOException {

        try {
          pieceRepo.save(new PieceBuilder()
                  .prefix(incoming.getPrefix())
                  .libNumber(incoming.getLibNumber())
                  .suffix(incoming.getSuffix())
                  .composerName(incoming.getComposerName())
                  .arranger(incoming.getArranger())
                  .title(incoming.getTitle())
                  .otherName(incoming.getOtherName())
                  .publisher(incoming.getPublisher())
                  .duration(incoming.getDuration())
                  .instrumentation(incoming.getInstrumentation())
                  .vocalistSoloist(incoming.getVocalistSoloist())
                  .percBreakdown(incoming.getPercBreakdown())
                  .notes(incoming.getNotes())
                  .status(incoming.getStatus())
                  .sign(incoming.getSign())
                  .updated(incoming.getUpdated())
                  .build());

        } catch (
                Exception error) {
            error.printStackTrace();
        }
        return (Collection<Piece>) pieceRepo.findAll();
    }


}
