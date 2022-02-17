package com.example.demo.legos;


import com.example.demo.basicModels.player.Player;
import com.example.demo.enums.Part;
import com.example.demo.legos.emptyChair.Chair;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PlayerInChair {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private ShowPiece showPiece;

    @ManyToOne
    private Chair chair;

//    private int rank;

//    @ElementCollection
//    private List<Part> parts = new ArrayList<>();
//
//    private Part primaryPart;

    public PlayerInChair() {
    }

    public PlayerInChair(ShowPiece showPiece, Chair chair) {
        this.showPiece = showPiece;
        this.chair = chair;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setShowPiece(ShowPiece showPiece) {
        this.showPiece = showPiece;
    }

//    public void setRank(int rank) {
//        this.rank = rank;
//    }
//
//    public void setParts(List<Part> parts) {
//        this.parts = parts;
//        this.primaryPart = parts.get(0);
//    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public ShowPiece getShowPiece() {
        return showPiece;
    }

//    public int getRank() {
//        return rank;
//    }
//
//    public List<Part> getParts() {
//        return parts;
//    }
//
//    public Part getPrimaryPart() {
//        return primaryPart;
//    }

    public Chair getChair() {
        return chair;
    }

    public boolean hasThisPlayer(Player incomingPlayer) {
        return player != null && player.equals(incomingPlayer);
    }

}
