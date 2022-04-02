package com.example.demo.repos;

import com.example.demo.basicModels.piece.Piece;
import com.example.demo.basicModels.show.Show;
import com.example.demo.legos.ShowPiece;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ShowPieceRepo extends CrudRepository<ShowPiece, Long> {

    Collection<ShowPiece> findAllByShow(Show incomingShow);

    boolean existsByPiece(Piece pieceForChairs);

    Collection<ShowPiece> findAllByPiece(Piece pieceForChairs);

    boolean existsByPieceAndShowAndOrderNum(Piece piece, Show show, int orderNum);

    boolean existsByPieceAndShow(Piece piece, Show show);

    ShowPiece findByPieceAndShow(Piece piece, Show show);
}
