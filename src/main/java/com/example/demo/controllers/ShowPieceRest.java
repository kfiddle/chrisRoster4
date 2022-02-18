package com.example.demo.controllers;


import com.example.demo.basicModels.player.Player;
import com.example.demo.basicModels.show.Show;
import com.example.demo.basicModels.show.ShowBuilder;
import com.example.demo.legos.PlayerInChair;
import com.example.demo.legos.ShowPiece;
import com.example.demo.legos.emptyChair.Chair;
import com.example.demo.legos.emptyChair.ChairBuilder;
import com.example.demo.repos.ChairRepo;
import com.example.demo.repos.PlayerInChairRepo;
import com.example.demo.repos.ShowPieceRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class ShowPieceRest {

    @Resource
    ShowPieceRepo showPieceRepo;

    @Resource
    ChairRepo chairRepo;

    @Resource
    PlayerInChairRepo picRepo;

    @RequestMapping("/get-all-show-pieces")
    public Collection<ShowPiece> getAllShowPieces() {
        return (Collection<ShowPiece>) showPieceRepo.findAll();
    }

    @PostMapping("/add-show-pieces")
    public Collection<ShowPiece> addShowPiecesToDatabase(@RequestBody Collection<ShowPiece> showPiecesToAdd) throws IOException {
        try {

            for (ShowPiece showPiece : showPiecesToAdd) {
                ShowPiece newShowPiece = new ShowPiece(showPiece.getPiece(), showPiece.getShow(), showPiece.getOrderNum());
                showPieceRepo.save(newShowPiece);

                if (chairRepo.existsByPiece(newShowPiece.getPiece())) {
                    for (Chair chair : chairRepo.findAllByPiece(newShowPiece.getPiece())) {
                        picRepo.save(new PlayerInChair(newShowPiece, chair));
                    }
                }

            }
        } catch (
                Exception error) {
            error.printStackTrace();
        }

        return (Collection<ShowPiece>) showPieceRepo.findAll();
    }

    @PostMapping("/get-pieces-on-program")
    public List<ShowPiece> getPiecesOnAShow(@RequestBody Show incomingShow) throws IOException {
        try {
            List<ShowPiece> showTunes = (List<ShowPiece>) showPieceRepo.findAllByShow(incomingShow);
            Collections.sort(showTunes);
            return showTunes;
        } catch (
                Exception error) {
            error.printStackTrace();
        }
        return null;
    }
}
