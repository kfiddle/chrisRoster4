package com.example.demo.legos;


import com.example.demo.basicModels.player.Player;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PlayerInChair {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Player player;

    @ManyToOne
    private ShowPiece showPiece;

    private int rank;

    public PlayerInChair() {
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setShowPiece(ShowPiece showPiece) {
        this.showPiece = showPiece;
    }

    public void setRank(int rank) {
        this.rank = rank;
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

    public int getRank() {
        return rank;
    }
}
