package com.example.demo.repos;

import com.example.demo.basicModels.show.Show;
import com.example.demo.legos.ShowPiece;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ShowPieceRepo extends CrudRepository<ShowPiece, Long> {

    Collection<ShowPiece> findAllByShow(Show incomingShow);
}
