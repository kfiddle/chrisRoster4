package com.example.demo.legos.emptyChair;


import com.example.demo.basicModels.piece.Piece;
import com.example.demo.enums.Part;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ChairBuilder {

    public Piece piece;

    public List<Part> parts = new ArrayList<>();

    public int rank;

    public ChairBuilder() {
    }

    public ChairBuilder piece(Piece piece) {
        Optional<Piece> pieceOpt = Optional.ofNullable(piece);
        pieceOpt.ifPresent(gotten -> this.piece = gotten);
        return this;
    }

    public ChairBuilder rank(int rank) {
        if (rank > 0) {
            this.rank = rank;
        }
        return this;
    }

    public ChairBuilder parts(List<Part> parts) {
        Optional<List<Part>> partsOpt = Optional.ofNullable(parts);
        partsOpt.ifPresent(gotten -> this.parts = gotten);
        return this;
    }

    public Chair build() {
        return new Chair(this);
    }




}
