package com.example.demo.basicModels.player;


import com.example.demo.enums.Part;
import com.example.demo.enums.Type;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private Type type;

    private int rank;

    @ElementCollection
    private List<Part> parts = new ArrayList<>();

    private Part primaryPart;

    private String firstNameArea;
    private String lastName;
    private String email;
    private String homePhone;
    private String cellPhone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zip;

    public Player() {
    }

    public Player(PlayerBuilder playerBuilder) {
        firstNameArea = playerBuilder.firstNameArea;
        lastName = playerBuilder.lastName;
        type = playerBuilder.type;
        rank = playerBuilder.rank;
        email = playerBuilder.email;
        homePhone = playerBuilder.homePhone;
        cellPhone = playerBuilder.cellPhone;
        addressLine1 = playerBuilder.addressLine1;
        addressLine2 = playerBuilder.addressLine2;
        city = playerBuilder.city;
        state = playerBuilder.state;
        zip = playerBuilder.zip;
        parts = playerBuilder.parts;
        if (playerBuilder.parts.size() > 0) {
            primaryPart = playerBuilder.parts.get(0);
        }
    }

}
