package com.example.demo.repos;

import com.example.demo.basicModels.piece.Piece;
import com.example.demo.legos.emptyChair.EmptyChair;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface EmptyChairRepo extends CrudRepository<EmptyChair, Long> {

    Collection<EmptyChair> findByPiece(Piece incomingPiece);
}
