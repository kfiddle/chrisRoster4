package com.example.demo.controllers;


import com.example.demo.basicModels.player.Player;
import com.example.demo.basicModels.player.PlayerBuilder;
import com.example.demo.basicModels.player.PlayerEditor;
import com.example.demo.enums.Part;
import com.example.demo.enums.Type;
import com.example.demo.repos.PlayerRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
public class PlayerRest {


    @Resource
    PlayerRepo playerRepo;

    @RequestMapping("/get-all-players")
    public Collection<Player> getAllPlayers() {
        return (Collection<Player>) playerRepo.findAll();
    }

    @RequestMapping("/get-all-parts")
    public Collection<Part> getAllParts() {
        return Arrays.asList(Part.values());
    }

    @PostMapping("/add-player")
    public Player addPlayerToDatabase(@RequestBody Player incomingPlayer) throws IOException {

        try {
            if (playerRepo.existsByFirstNameAreaAndLastName(incomingPlayer.getFirstNameArea(), incomingPlayer.getLastName())) {
                return playerRepo.findByFirstNameAreaAndLastName(incomingPlayer.getFirstNameArea(), incomingPlayer.getLastName());
            } else {

                playerRepo.save(new PlayerBuilder().firstNameArea(incomingPlayer.getFirstNameArea())
                        .lastName(incomingPlayer.getLastName())
                        .type(incomingPlayer.getType())
                        .parts(incomingPlayer.getParts())
                        .rank(incomingPlayer.getRank())
                        .email(incomingPlayer.getEmail())
                        .homePhone(incomingPlayer.getHomePhone())
                        .cellPhone(incomingPlayer.getCellPhone())
                        .addressLine1(incomingPlayer.getAddressLine1())
                        .addressLine2(incomingPlayer.getAddressLine2())
                        .city(incomingPlayer.getCity())
                        .state(incomingPlayer.getState())
                        .zip(incomingPlayer.getZip())
                        .build());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return playerRepo.findByFirstNameAreaAndLastName(incomingPlayer.getFirstNameArea(), incomingPlayer.getLastName());
    }

    @RequestMapping("/get-all-contracted-players")
    public Collection<Player> getAllContractedPlayers() {
        List<Player> playersToSendBack = (List<Player>) playerRepo.findAllByType(Type.CONTRACTED);
        Collections.sort(playersToSendBack);

        return playersToSendBack;
    }

    @RequestMapping("/get-all-sub-players")
    public Collection<Player> getAllSubs() {
        return playerRepo.findAllByType(Type.SUB);
    }

    @RequestMapping("/subs/{incomingPart}")
    public Collection<Player> getSubsOfInstrument(@PathVariable Part incomingPart) {
        Collection<Player> playersToSend = new ArrayList<>();
        for (Player player : playerRepo.findAllByType(Type.SUB)) {
            for (Part part : player.getParts()) {
                if (part.equals(incomingPart)) {
                    playersToSend.add(player);
                }
            }
        }
        return playersToSend;
    }


    @PostMapping("/edit-player")
    public Collection<Player> editPlayerInDatabase(@RequestBody Player incomingPlayer) throws IOException {
        try {
            Optional<Player> playerToFind = playerRepo.findById(incomingPlayer.getId());
            if (playerToFind.isPresent()) {
                Player playerToEdit = playerToFind.get();
                PlayerEditor editor = new PlayerEditor(playerToEdit);
                editor.editFrom(incomingPlayer);
            }
            return (Collection<Player>) playerRepo.findAll();

        } catch (Exception error) {
            error.printStackTrace();
        }
        return (Collection<Player>) playerRepo.findAll();
    }


}
