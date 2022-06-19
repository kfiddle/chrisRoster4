package com.example.demo.repos;

import com.example.demo.basicModels.gigOffer.GigOffer;
import com.example.demo.basicModels.player.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface GigOfferRepo extends CrudRepository<GigOffer, Long> {

    Collection<GigOffer> findAllByPlayer(Player player);
}
