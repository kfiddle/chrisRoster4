package com.example.demo.controllers;


import com.example.demo.basicModels.piece.Piece;
import com.example.demo.basicModels.piece.PieceBuilder;
import com.example.demo.basicModels.piece.PieceEditor;
import com.example.demo.basicModels.player.Player;
import com.example.demo.basicModels.player.PlayerEditor;
import com.example.demo.repos.PieceRepo;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

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

    @PostMapping("/edit-piece")
    public Collection<Piece> editPlayerInDatabase(@RequestBody Piece incoming) throws IOException {
        try {
            Optional<Piece> pieceToFind = pieceRepo.findById(incoming.getId());
            if (pieceToFind.isPresent()) {
                Piece pieceToEdit = pieceToFind.get();
                PieceEditor editor = new PieceEditor(pieceToEdit);
                editor.editFrom(incoming);
            }
            return (Collection<Piece>) pieceRepo.findAll();

        } catch (Exception error) {
            error.printStackTrace();
        }
        return (Collection<Piece>) pieceRepo.findAll();
    }

    @RequestMapping("get-sorted-pieces/{sortType}")
    public Collection<Piece> getSortedPieces(@PathVariable String sortType) {
        try {
            return pieceRepo.findAllBy(Sort.by(sortType));
        } catch (
                Exception error) {
            error.printStackTrace();
        }
        return null;
    }


}
