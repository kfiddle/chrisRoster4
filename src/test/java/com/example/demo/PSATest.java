package com.example.demo;


import com.example.demo.basicModels.piece.Piece;
import com.example.demo.basicModels.piece.PieceBuilder;
import com.example.demo.basicModels.player.Player;
import com.example.demo.basicModels.player.PlayerBuilder;
import com.example.demo.basicModels.show.Show;
import com.example.demo.basicModels.show.ShowBuilder;
import com.example.demo.enums.Part;
import com.example.demo.enums.Type;
import com.example.demo.legos.ShowPiece;
import com.example.demo.legos.emptyChair.Chair;
import com.example.demo.legos.emptyChair.ChairBuilder;
import com.example.demo.legos.playerInChair.PlayerInChair;
import com.example.demo.repos.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@SpringBootTest
@AutoConfigureMockMvc
public class PSATest {

    @Autowired
    private PlayerRepo playerRepo;

    @Autowired
    private PieceRepo pieceRepo;

    @Autowired
    private ShowRepo showRepo;

    @Autowired
    private ShowPieceRepo showPieceRepo;

    @Autowired
    private ChairRepo chairRepo;

    @Autowired
    private PlayerInChairRepo picRepo;

    @Resource
    private MockMvc mockMvc;

//    public Chair(ChairBuilder chairBuilder) {
//        this.piece = chairBuilder.piece;
//        this.show = chairBuilder.show;
//        this.rank = chairBuilder.rank;
//        this.parts = chairBuilder.parts;
//        this.primaryPart = parts.get(0);
//        this.specialDesignate = chairBuilder.specialDesignate;
//    }

    @Test
    public void mustMakeGigOfferToSinglePlayer() throws Exception {

//        Player leAnne = new PlayerBuilder().firstNameArea("Leanne").lastName("Wistrom").type(Type.CONTRACTED).rank(1).addAPart(Part.FLUTE).build();
//        playerRepo.save(leAnne);

        Show firstShow = new ShowBuilder().build();
        showRepo.save(firstShow);

        Piece firebird = new PieceBuilder().build();
        pieceRepo.save(firebird);

        ShowPiece birdOnFirst = new ShowPiece(firebird, firstShow);
        showPieceRepo.save(birdOnFirst);

        Chair fluteChair = new ChairBuilder().piece(firebird).parts(Collections.singletonList(Part.FLUTE)).rank(1).build();
        chairRepo.save(fluteChair);

        PlayerInChair flutePart = new PlayerInChair(birdOnFirst, fluteChair);
        picRepo.save(flutePart);

        Player lWistrom = playerRepo.findByFirstNameAreaAndLastName("Leanne", "Wistrom");
        Long leanneId = lWistrom.getId();

        assertThat(fluteChair.getPrimaryPart().equals(lWistrom.getPrimaryPart())).isTrue();
        assertThat(fluteChair.getRank() == lWistrom.getRank()).isTrue();

     Collection<PlayerInChair> pics = picRepo.findAllByShowPiece(birdOnFirst);

//        restUserMockMvc
//                .perform(get("/api/account").accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.login").value("test"))
//                .andExpect(jsonPath("$.firstName").value("john"))
//                .andExpect(jsonPath("$.lastName").value("doe"))
//                .andExpect(jsonPath("$.email").value("john.doe@jhipter.com"))
//                .andExpect(jsonPath("$.authorities").value(AuthoritiesConstants.ADMIN));

//        mockMvc.perform(post("/login")
//                .content("{\"username\":\"cn@Email\",\"password\":\"ChrisPass\"}")
//                .header(HttpHeaders.CONTENT_TYPE, "application/json"))
//                .andDo(print()).andExpect(status().isOk());

//        mockMvc.perform(get("/compute-offers-for-player/" + leanneId))
//                .andExpect(content().string(containsString("Flute")));
    }
}
