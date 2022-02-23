package com.example.demo.legos;


import com.example.demo.basicModels.player.Player;
import com.example.demo.enums.Part;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayerChair2 {


    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private ShowPiece showPiece;

    @ElementCollection
    private List<Part> parts = new ArrayList<>();

    @ManyToOne
    private Player player;

    private int rank;

    public PlayerChair2() {
    }

    public PlayerChair2(ShowPiece showPiece, Part part) {
        this.showPiece = showPiece;
        parts.add(part);
    }

    public void setShowPiece(ShowPiece showPiece) {
        this.showPiece = showPiece;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Long getId() {
        return id;
    }

    public ShowPiece getShowPiece() {
        return showPiece;
    }

    public List<Part> getParts() {
        return parts;
    }

    public Player getPlayer() {
        return player;
    }

    public int getRank() {
        return rank;
    }
}
