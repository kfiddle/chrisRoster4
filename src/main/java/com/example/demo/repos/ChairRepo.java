package com.example.demo.repos;

import com.example.demo.basicModels.piece.Piece;
import com.example.demo.legos.emptyChair.Chair;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ChairRepo extends CrudRepository<Chair, Long> {

    Collection<Chair> findByPiece(Piece incomingPiece);
}
