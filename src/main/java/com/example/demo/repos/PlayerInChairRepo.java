package com.example.demo.repos;

import com.example.demo.legos.PlayerInChair;
import com.example.demo.legos.ShowPiece;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PlayerInChairRepo extends CrudRepository<PlayerInChair, Long> {

    Collection<PlayerInChair> findAllByShowPiece(ShowPiece showPiece);
}
