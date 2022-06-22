package com.example.demo.controllers;


import com.example.demo.basicModels.gigOffer.GigOffer;
import com.example.demo.basicModels.logEvent.LogEvent;
import com.example.demo.basicModels.player.Player;
import com.example.demo.basicModels.show.Horloge;
import com.example.demo.basicModels.show.Show;
import com.example.demo.enums.Event;
import com.example.demo.enums.Part;
import com.example.demo.enums.Reply;
import com.example.demo.legos.ShowPiece;
import com.example.demo.legos.playerInChair.PlayerInChair;
import com.example.demo.repos.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.*;

@CrossOrigin
@RestController
public class GigOfferRest {


    @Resource
    private GigOfferRepo gigOfferRepo;

    @Resource
    private LogEventRepo logEventRepo;

    @Resource
    private ShowRepo showRepo;

    @Resource
    private ShowPieceRepo showPieceRepo;

    @Resource
    private PlayerRepo playerRepo;

    @Resource
    private PlayerInChairRepo picRepo;

    @Resource
    private HorlogeRepo horlogeRepo;

    @RequestMapping("/get-all-gig-offers")
    public Collection<GigOffer> getAllOffers() throws IOException {
        return (Collection<GigOffer>) gigOfferRepo.findAll();
    }

    @RequestMapping("/delete-all-gig-offers")
    public String deleteAllOffers() throws IOException {
        gigOfferRepo.deleteAll();
        return "All is lost";
    }

    @RequestMapping("/offers-by-player/{playerId}")
    public List<GigOffer> findAllOffersMadeToPlayer(@PathVariable Long playerId) throws IOException {
        List<GigOffer> offersToReturn = new ArrayList<>();

        try {
            Optional<Player> playerToFind = playerRepo.findById(playerId);
            if (playerToFind.isPresent()) {
                Collection<GigOffer> offers = gigOfferRepo.findAllByPlayer(playerToFind.get());
                if (offers.size() > 0) {
                    for (Horloge horloge : horlogeRepo.findAllByEventOrderByDate(Event.PRIMARYDATE)) {
                        for (GigOffer offer : offers) {
                            if (horloge.getShow().equals(offer.getShow())) {
                                offersToReturn.add(offer);
                            }
                        }
                    }
                    return offersToReturn;
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }

    @RequestMapping("/compute-offers-for-player/{playerId}")
    public String computeOffersForPlayer(@PathVariable Long playerId) throws IOException {

        try {

            Optional<Player> playerToFind = playerRepo.findById(playerId);
            if (playerToFind.isPresent()) {
                Player playerToOffer = playerToFind.get();

                Collection<GigOffer> gigsToOffer = new ArrayList<>();

                for (Show show : showRepo.findAll()) {
                    Collection<PlayerInChair> picsInShow = picRepo.findAllByShow(show);

                    for (PlayerInChair pic : picsInShow) {
                        if (pic.getChair().getPrimaryPart().equals(playerToOffer.getPrimaryPart()) &&
                                pic.getChair().getRank() == playerToOffer.getRank()) {
                            return "TRUE";
                        }
                    }
                }
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return "nope";
    }

    private void fillChair(GigOffer offerToSetReply) {
        Player playerToSit = offerToSetReply.getPlayer();
        int playerRank = playerToSit.getRank();
        Part playerPart = playerToSit.getPrimaryPart();

        System.out.println(offerToSetReply.getPlayer().getLastName());

        //for pops only

        Collection<PlayerInChair> picsToFill = picRepo.findAllByShow(offerToSetReply.getShow());
        for (PlayerInChair pic : picsToFill) {
            if (pic.getChair().getRank() == playerRank && pic.getChair().getPrimaryPart().equals(playerPart)) {
                pic.setPlayer(playerToSit);
                System.out.println("We have a winner");
                picRepo.save(pic);
            }
        }

        for (ShowPiece showPiece : showPieceRepo.findAllByShow(offerToSetReply.getShow())) {
            for (PlayerInChair pic : picRepo.findAllByShowPiece(showPiece)) {
                if (pic.getChair().getRank() == playerRank && pic.getChair().getPrimaryPart().equals(playerPart)) {
                    pic.setPlayer(playerToSit);
                    System.out.println("We have a winner in this now");
                    picRepo.save(pic);
                }
            }
        }
    }

    @PostMapping("/gig-offer-reply")
    public GigOffer logPlayerResponseToGigOffer(@RequestBody GigOffer incomingReply) throws IOException {

        //don't forget to set response date too

        try {
            Optional<GigOffer> offerToFind = gigOfferRepo.findById(incomingReply.getId());
            LocalDate currentDate = LocalDate.now();
            if (offerToFind.isPresent()) {
                GigOffer offerToSetReply = offerToFind.get();
                offerToSetReply.setReply(incomingReply.getReply());
                offerToSetReply.setResponseDate(currentDate);
                gigOfferRepo.save(offerToSetReply);

                if (offerToSetReply.getReply().equals(Reply.ACCEPT)) {
                    fillChair(offerToSetReply);
                }

                LogEvent newEvent = new LogEvent(offerToSetReply, currentDate);
                logEventRepo.save(newEvent);

                return offerToSetReply;
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        return null;
    }


}
