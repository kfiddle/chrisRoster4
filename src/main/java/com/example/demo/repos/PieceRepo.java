package com.example.demo.repos;

import com.example.demo.basicModels.piece.Piece;
import org.springframework.data.repository.CrudRepository;

public interface PieceRepo extends CrudRepository<Piece, Long> {

}
