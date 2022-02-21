package com.example.demo.controllers;


import com.example.demo.basicModels.piece.Piece;
import com.example.demo.basicModels.player.Player;
import com.example.demo.enums.Type;
import com.example.demo.legos.ShowPiece;
import com.example.demo.legos.emptyChair.Chair;
import com.example.demo.legos.PlayerInChair;
import com.example.demo.legos.emptyChair.ChairBuilder;
import com.example.demo.repos.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
public class ChairsRest {

    @Resource
    PlayerInChairRepo picRepo;

    @Resource
    PieceRepo pieceRepo;

    @Resource
    ShowPieceRepo showPieceRepo;

    @Resource
    ChairRepo chairRepo;

    @Resource
    PlayerRepo playerRepo;

    @RequestMapping("/get-chairs-in-show-piece")
    public Collection<PlayerInChair> getAllChairsInAPieceOnShow(@RequestBody ShowPiece incomingShowPiece) {
        Optional<ShowPiece> showPieceToFind = showPieceRepo.findById(incomingShowPiece.getId());
        return showPieceToFind.map(showPiece -> (Collection<PlayerInChair>) picRepo.findAllByShowPiece(showPiece)).orElse(null);
    }

    @RequestMapping("/get-orchestration-in-piece")
    public Collection<Chair> getEmptyChairsNeededInPiece(@RequestBody Piece incomingPiece) {
        return chairRepo.findByPiece(incomingPiece);
    }

    @PostMapping("/add-empty-chairs/{pieceId}")
    public Optional<Piece> addFullOrchestration(@PathVariable Long pieceId, @RequestBody Collection<Chair> incomingChairs) throws IOException {
        Optional<Piece> pieceCheck = pieceRepo.findById(pieceId);

        try {
            if (pieceCheck.isPresent()) {
                Piece pieceForChairs = pieceCheck.get();

                for (Chair chair : incomingChairs) {
                    Chair chairToSave = new ChairBuilder()
                            .parts(chair.getParts())
                            .rank(chair.getRank())
                            .piece(pieceForChairs)
                            .build();
                    chairRepo.save(chairToSave);

                    if (showPieceRepo.existsByPiece(pieceForChairs)) {
                        for (ShowPiece showPiece : showPieceRepo.findAllByPiece(pieceForChairs)) {
                            picRepo.save(new PlayerInChair(showPiece, chairToSave));
                        }
                    }
                }
            }
        } catch (
                Exception error) {
            error.printStackTrace();
        }
        return pieceCheck;
    }

    @PostMapping("/get-possible-players")
    public List<Player> getPossiblePlayersForAChair(@RequestBody PlayerInChair incomingPIC) {

        try {
            List<Player> playersToSend = new ArrayList<>();
            Optional<PlayerInChair> picToFind = picRepo.findById(incomingPIC.getId());
            if (picToFind.isPresent()) {
                PlayerInChair foundPIC = picToFind.get();

                HashMap<Player, Boolean> eligiblePlayers = new HashMap<>();
                for (Player player : playerRepo.findAllByType(Type.CONTRACTED)) {
                    eligiblePlayers.put(player, true);
                }

                for (PlayerInChair picToCheck : picRepo.findAllByShowPiece(foundPIC.getShowPiece())) {
                    if (eligiblePlayers.containsKey(picToCheck.getPlayer())) {
                        eligiblePlayers.put(picToCheck.getPlayer(), false);
                    }
                }

                for (Player player : playerRepo.findAllByType(Type.CONTRACTED)) {
                    if (!player.couldSitHere(foundPIC)) {
                        eligiblePlayers.put(player, false);
                    }
                }

                for (Map.Entry<Player, Boolean> entry : eligiblePlayers.entrySet()) {
                    if (entry.getValue().equals(true)) {
                        playersToSend.add(entry.getKey());
                    }
                }

            }
            Collections.sort(playersToSend);
            return playersToSend;


        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    @PostMapping("/put-player-in-pic/{picId}")
    public Optional<ShowPiece> putAPlayerInAChair(@RequestBody Player incomingPlayer, @PathVariable Long picId) {
        try {
            Optional<PlayerInChair> premadePIC = picRepo.findById(picId);
            Optional<Player> playerToFind = playerRepo.findById(incomingPlayer.getId());
            if (premadePIC.isPresent() && playerToFind.isPresent()) {
                PlayerInChair pic = premadePIC.get();
                pic.setPlayer(playerToFind.get());
                picRepo.save(pic);
                return showPieceRepo.findById(pic.getShowPiece().getId());
            }
        } catch (
                Exception error) {
            error.printStackTrace();

        }
        return Optional.empty();
    }

//    @PostMapping("/make-string-chairs")
//    public Collection<PlayerInChair> makeChairs (@RequestBody Collection<PlayerInChair> incomingStringSeats) throws IOException {
//
//
//    }


}
