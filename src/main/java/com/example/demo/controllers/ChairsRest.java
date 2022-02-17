package com.example.demo.controllers;


import com.example.demo.basicModels.piece.Piece;
import com.example.demo.basicModels.show.ShowBuilder;
import com.example.demo.legos.emptyChair.EmptyChair;
import com.example.demo.legos.PlayerInChair;
import com.example.demo.legos.emptyChair.EmptyChairBuilder;
import com.example.demo.repos.EmptyChairRepo;
import com.example.demo.repos.PieceRepo;
import com.example.demo.repos.PlayerInChairRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
public class ChairsRest {

    @Resource
    PlayerInChairRepo picRepo;

    @Resource
    PieceRepo pieceRepo;

    @Resource
    EmptyChairRepo emptyChairRepo;

    @RequestMapping("/get-chairs-in-show-piece")
    public Collection<PlayerInChair> getAllChairsInAPieceOnShow() {
        return (Collection<PlayerInChair>) picRepo.findAll();
    }

    @RequestMapping("/get-orchestration-in-piece")
    public Collection<EmptyChair> getEmptyChairsNeededInPiece(@RequestBody Piece incomingPiece) {
        return emptyChairRepo.findByPiece(incomingPiece);
    }

    @PostMapping("/add-all-empty-chairs/{pieceId}")
    public Optional<Piece> addFullOrchestration(@PathVariable Long pieceId, @RequestBody Collection<EmptyChair> incomingChairs) throws IOException {
        Optional<Piece> pieceCheck = pieceRepo.findById(pieceId);

        try {
            if (pieceCheck.isPresent()) {
                Piece pieceForChairs = pieceCheck.get();

                for (EmptyChair emptyChair : incomingChairs) {
                    emptyChairRepo.save(new EmptyChairBuilder()
                            .parts(emptyChair.getParts())
                            .rank(emptyChair.getRank())
                            .piece(pieceForChairs)
                            .build());
                }
            }
        } catch (
                Exception error) {
            error.printStackTrace();
        }
        return pieceCheck;
    }

}
