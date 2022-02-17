package com.example.demo.legos.emptyChair;


import com.example.demo.basicModels.piece.Piece;
import com.example.demo.enums.Part;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmptyChairBuilder {

    public Piece piece;

    public List<Part> parts = new ArrayList<>();

    public int rank;

    public EmptyChairBuilder() {
    }

    public EmptyChairBuilder piece(Piece piece) {
        Optional<Piece> pieceOpt = Optional.ofNullable(piece);
        pieceOpt.ifPresent(gotten -> this.piece = gotten);
        return this;
    }

    public EmptyChairBuilder rank(int rank) {
        if (rank > 0) {
            this.rank = rank;
        }
        return this;
    }

    public EmptyChairBuilder parts(List<Part> parts) {
        Optional<List<Part>> partsOpt = Optional.ofNullable(parts);
        partsOpt.ifPresent(gotten -> this.parts = gotten);
        return this;
    }

    public EmptyChair build() {
        return new EmptyChair(this);
    }




}
