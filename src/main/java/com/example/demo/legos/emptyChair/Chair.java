package com.example.demo.legos.emptyChair;

import com.example.demo.basicModels.piece.Piece;
import com.example.demo.basicModels.player.Player;
import com.example.demo.basicModels.show.Show;
import com.example.demo.enums.Part;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chair {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Piece piece;

    @ManyToOne
    private Show show;

    private int rank;

    @ElementCollection
    private List<Part> parts = new ArrayList<>();

    private Part primaryPart;

    public Chair() {
    }

    public Chair(ChairBuilder chairBuilder) {
        this.piece = chairBuilder.piece;
        this.show = chairBuilder.show;
        this.rank = chairBuilder.rank;
        this.parts = chairBuilder.parts;
        this.primaryPart = parts.get(0);
    }


    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
        primaryPart = parts.get(0);
    }

    public void setPrimaryPart(Part primaryPart) {
        this.primaryPart = primaryPart;
    }

    public Long getId() {
        return id;
    }

    public Piece getPiece() {
        return piece;
    }

    public int getRank() {
        return rank;
    }

    public List<Part> getParts() {
        return parts;
    }

    public Part getPrimaryPart() {
        return parts.get(0);
    }
}
