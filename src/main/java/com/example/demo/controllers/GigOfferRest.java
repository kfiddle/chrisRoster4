package com.example.demo.controllers;


import com.example.demo.basicModels.gigOffer.GigOffer;
import com.example.demo.basicModels.player.Player;
import com.example.demo.repos.GigOfferRepo;
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


}
