package com.example.demo.repos;

import com.example.demo.basicModels.piece.Piece;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface PieceRepo extends CrudRepository<Piece, Long> {

    Collection<Piece> findAllBy(Sort by);
}
