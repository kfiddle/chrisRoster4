package com.example.demo.basicModels;


import com.example.demo.enums.Part;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Strumento implements Comparable<Strumento> {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int scoreOrder;

    public Strumento() {
    }

    public Strumento(String name) {
        this.name = name;
    }

    public Strumento(String name, int scoreOrder) {
        this.name = name;
        this.scoreOrder = scoreOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScoreOrder(int scoreOrder) {
        this.scoreOrder = scoreOrder;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScoreOrder() {
        return scoreOrder;
    }

    @Override
    public int compareTo(Strumento other) {
        return Integer.compare(this.scoreOrder, other.getScoreOrder());
    }

}
