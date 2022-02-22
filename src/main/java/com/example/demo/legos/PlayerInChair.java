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

    private int sectionSeat;

    public PlayerInChair() {
    }

    public PlayerInChair(ShowPiece showPiece, Chair chair) {
        this.showPiece = showPiece;
        this.chair = chair;
    }

    public PlayerInChair(ShowPiece showPiece, Chair chair, int sectionSeat) {
        this.showPiece = showPiece;
        this.chair = chair;
        this.sectionSeat = sectionSeat;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setShowPiece(ShowPiece showPiece) {
        this.showPiece = showPiece;
    }

    public void setChair(Chair chair) {
        this.chair = chair;
    }

    public void setSectionSeat(int sectionSeat) {
        this.sectionSeat = sectionSeat;
    }

    public Long getId() {
        return id;
    }

    public Player getPlayer() {
        return player;
    }

    public ShowPiece getShowPiece() {
        return showPiece;
    }

    public Chair getChair() {
        return chair;
    }

    public int getSectionSeat() {
        return sectionSeat;
    }

    public boolean hasThisPlayer(Player incomingPlayer) {
        return player != null && player.equals(incomingPlayer);
    }

}
