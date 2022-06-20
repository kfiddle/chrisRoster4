package com.example.demo.controllers;


import com.example.demo.basicModels.gigOffer.GigOffer;
import com.example.demo.basicModels.player.Player;
import com.example.demo.basicModels.show.Show;
import com.example.demo.legos.playerInChair.PlayerInChair;
import com.example.demo.repos.GigOfferRepo;
import com.example.demo.repos.PlayerInChairRepo;
import com.example.demo.repos.PlayerRepo;
import com.example.demo.repos.ShowRepo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@CrossOrigin
@RestController
public class GigOfferRest {


    @Resource
    private GigOfferRepo gigOfferRepo;

    @Resource
    private ShowRepo showRepo;

    @Resource
    private PlayerRepo playerRepo;

    @Resource
    private PlayerInChairRepo picRepo;

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

        try {
            Optional<Player> playerToFind = playerRepo.findById(playerId);
            if (playerToFind.isPresent()) {
                List<GigOffer> offersMade = (List<GigOffer>) gigOfferRepo.findAllByPlayer(playerToFind.get());
                if (offersMade.size() > 0) {
                    Collections.sort(offersMade);
                    return offersMade;
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


}
