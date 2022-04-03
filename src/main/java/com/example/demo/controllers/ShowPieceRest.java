package com.example.demo.controllers;


import com.example.demo.basicModels.piece.Piece;
import com.example.demo.basicModels.show.Show;
import com.example.demo.legos.playerInChair.PlayerInChair;
import com.example.demo.legos.ShowPiece;
import com.example.demo.legos.emptyChair.Chair;
import com.example.demo.repos.ChairRepo;
import com.example.demo.repos.PlayerInChairRepo;
import com.example.demo.repos.ShowPieceRepo;
import com.example.demo.repos.ShowRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
public class ShowPieceRest {

    @Resource
    ShowPieceRepo showPieceRepo;

    @Resource
    ShowRepo showRepo;

    @Resource
    ChairRepo chairRepo;

    @Resource
    PlayerInChairRepo picRepo;

    @RequestMapping("/get-all-show-pieces")
    public Collection<ShowPiece> getAllShowPieces() {
        return (Collection<ShowPiece>) showPieceRepo.findAll();
    }

    public ShowPiece addAShowPiece(ShowPiece showPieceToAdd) {
        ShowPiece newShowPiece = new ShowPiece(showPieceToAdd.getPiece(), showPieceToAdd.getShow(), showPieceToAdd.getOrderNum());
        showPieceRepo.save(newShowPiece);


        if (chairRepo.existsByPiece(newShowPiece.getPiece())) {
            for (Chair chair : chairRepo.findAllByPiece(newShowPiece.getPiece())) {
                picRepo.save(new PlayerInChair(newShowPiece, chair));
            }
        }
        return newShowPiece;
    }

    @PostMapping("/add-show-piece")
    public ShowPiece addSingleShowPiece(@RequestBody ShowPiece showPieceToAdd) throws IOException {

        try {
            return addAShowPiece(showPieceToAdd);
        } catch (
                Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    @PostMapping("/edit-showpiece-ordernum/{orderNum}")
    public Optional<ShowPiece> editWhereOnProgram(@PathVariable int orderNum, @RequestBody ShowPiece incoming) throws IOException {
        Optional<ShowPiece> showPieceToFind = showPieceRepo.findById(incoming.getId());
        if (showPieceToFind.isPresent()) {
            ShowPiece showPieceToChange = showPieceToFind.get();
            showPieceToChange.setOrderNum(orderNum);
            showPieceRepo.save(showPieceToChange);

            for (ShowPiece showPiece : showPieceRepo.findAll()) {
                System.out.println(showPiece.getPiece().getTitle() + "    " + showPiece.getOrderNum());
            }
        }
        return showPieceToFind;
    }




    @PostMapping("/remove-showpiece")
    public String deleteShowPieceFromDB(@RequestBody Show incomingShow) throws IOException {
        Optional<ShowPiece> showPieceToFind = showPieceRepo.findById(incomingShow.getId());

        if (showPieceToFind.isPresent()) {
            showPieceRepo.delete(showPieceToFind.get());
            return "returned string";
        }
        return "nothing was deleted";
    }


    @PostMapping("/get-showtunes-on-program")
    public List<ShowPiece> getShowTunesOnAShow(@RequestBody Show incomingShow) throws IOException {
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

    @PostMapping("/get-pieces-on-program")
    public List<Piece> getPiecesOnAShow(@RequestBody Show incomingShow) throws IOException {

        List<Piece> piecesToReturn = new ArrayList<>();

        try {
            List<ShowPiece> showTunes = (List<ShowPiece>) showPieceRepo.findAllByShow(incomingShow);
            Collections.sort(showTunes);

            for (ShowPiece showPiece : showTunes) {
                piecesToReturn.add(showPiece.getPiece());
            }

        } catch (Exception error) {
            error.printStackTrace();
        }

        return piecesToReturn;
    }


}

//    @PostMapping("/add-show-piece")
//    public ShowPiece addSingleShowPiece(@RequestBody ShowPiece showPieceToAdd) throws IOException {
//
//        try {
//            ShowPiece newShowPiece = new ShowPiece(showPieceToAdd.getPiece(), showPieceToAdd.getShow(), showPieceToAdd.getOrderNum());
//            showPieceRepo.save(newShowPiece);
//
//            if (chairRepo.existsByPiece(newShowPiece.getPiece())) {
//                for (Chair chair : chairRepo.findAllByPiece(newShowPiece.getPiece())) {
//                    picRepo.save(new PlayerInChair(newShowPiece, chair));
//                }
//            }
//            return newShowPiece;
//        } catch (
//                Exception error) {
//            error.printStackTrace();
//        }
//        return null;
//    }

//    @PostMapping("/add-show-pieces")
//    public Collection<ShowPiece> addShowPiecesToDatabase(@RequestBody Collection<ShowPiece> showPiecesToAdd) throws IOException {
//        try {
//            for (ShowPiece showPiece : showPiecesToAdd) {
//                ShowPiece newShowPiece = new ShowPiece(showPiece.getPiece(), showPiece.getShow(), showPiece.getOrderNum());
//                showPieceRepo.save(newShowPiece);
//
//                if (chairRepo.existsByPiece(newShowPiece.getPiece())) {
//                    for (Chair chair : chairRepo.findAllByPiece(newShowPiece.getPiece())) {
//                        picRepo.save(new PlayerInChair(newShowPiece, chair));
//                    }
//                }
//
//            }
//        } catch (
//                Exception error) {
//            error.printStackTrace();
//        }
//
//        return (Collection<ShowPiece>) showPieceRepo.findAll();
//    }
