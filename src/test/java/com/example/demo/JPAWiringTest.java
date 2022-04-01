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

import java.util.*;

@DataJpaTest
public class JPAWiringTest {

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


    @Test
    public void mustSeeEligiblePlayersForChair() {


        Player leAnne = new PlayerBuilder().firstNameArea("Leanne").lastName("Wistrom").type(Type.CONTRACTED).rank(1).addAPart(Part.FLUTE).build();
        Player seanG = new PlayerBuilder().firstNameArea("Sean").lastName("Gabriel").type(Type.CONTRACTED).rank(2).addAPart(Part.FLUTE).build();
        Player danna = new PlayerBuilder().firstNameArea("Danna").lastName("Sundet").type(Type.CONTRACTED).rank(1).addAPart(Part.OBOE).build();
        Player heatherS = new Player(new PlayerBuilder().firstNameArea("Heather").lastName("Story").type(Type.CONTRACTED).rank(2).addAPart(Part.OBOE));
        Player sarahH = new Player(new PlayerBuilder().firstNameArea("Sarah").lastName("Hamilton").type(Type.CONTRACTED).rank(3).addAPart(Part.OBOE).addAPart(Part.ENGLISHHORN));
        Player ami = new Player(new PlayerBuilder().firstNameArea("Ami").lastName("Vardi").type(Type.CONTRACTED).rank(1).addAPart(Part.CLARINET));
        Player benC = new Player(new PlayerBuilder().firstNameArea("Benjamin").lastName("Chen").type(Type.CONTRACTED).rank(1).addAPart(Part.CLARINET));
        Player db = new Player(new PlayerBuilder().firstNameArea("David").lastName("Boutin-Bourque").type(Type.CONTRACTED).rank(3).addAPart(Part.CLARINET).addAPart(Part.BASSCLARINET));
        Player kdo = new Player(new PlayerBuilder().firstNameArea("KeriAnn").lastName("DiBari-Oberle").type(Type.CONTRACTED).rank(4).addAPart(Part.CLARINET).addAPart(Part.EBCLARINET));
        Player lk = new Player(new PlayerBuilder().firstNameArea("Laura").lastName("Koepke").type(Type.CONTRACTED).rank(1).addAPart(Part.BASSOON));
        Player lel = new Player(new PlayerBuilder().firstNameArea("Sarah Elizabeth").lastName("Lee").type(Type.CONTRACTED).rank(2).addAPart(Part.BASSOON));
        Player cr = new Player(new PlayerBuilder().firstNameArea("Chris").lastName("Rapier").type(Type.CONTRACTED).rank(1).addAPart(Part.HORN));
        Player ma = new Player(new PlayerBuilder().firstNameArea("Mark").lastName("Addleman").type(Type.CONTRACTED).rank(2).addAPart(Part.HORN));
        Player es = new Player(new PlayerBuilder().firstNameArea("Emily").lastName("Shelley").type(Type.CONTRACTED).rank(3).addAPart(Part.HORN));
        Player ba = new Player(new PlayerBuilder().firstNameArea("Bryan").lastName("Adkins").type(Type.CONTRACTED).rank(4).addAPart(Part.HORN));
        Player bs = new Player(new PlayerBuilder().firstNameArea("Benjamin").lastName("Strecker").type(Type.CONTRACTED).rank(5).addAPart(Part.HORN));
        Player gd = new Player(new PlayerBuilder().firstNameArea("Gary").lastName("Davis").type(Type.CONTRACTED).rank(1).addAPart(Part.TRUMPET));
        Player da = new Player(new PlayerBuilder().firstNameArea("Douglas").lastName("Amos").type(Type.CONTRACTED).rank(2).addAPart(Part.TRUMPET));
        Player mh = new Player(new PlayerBuilder().firstNameArea("Micah").lastName("Holt").type(Type.CONTRACTED).rank(3).addAPart(Part.TRUMPET));
        Player wc = new Player(new PlayerBuilder().firstNameArea("Whitney").lastName("Claire").type(Type.CONTRACTED).rank(1).addAPart(Part.TROMBONE));
        Player sb = new Player(new PlayerBuilder().firstNameArea("Sean").lastName("Bessette").type(Type.CONTRACTED).rank(1).addAPart(Part.BASSTROMBONE));
        Player kh = new Player(new PlayerBuilder().firstNameArea("Ken").lastName("Heinlein").type(Type.CONTRACTED).rank(1).addAPart(Part.TUBA));
        Player bradA = new Player(new PlayerBuilder().firstNameArea("Brad").lastName("Amidon").type(Type.CONTRACTED).rank(1).addAPart(Part.PERCUSSION));
        Player ml = new Player(new PlayerBuilder().firstNameArea("Matt").lastName("Larson").type(Type.CONTRACTED).rank(2).addAPart(Part.PERCUSSION));
        Player mr = new Player(new PlayerBuilder().firstNameArea("Melody").lastName("Rapier").type(Type.CONTRACTED).rank(1).addAPart(Part.HARP));
        Player kj = new Player(new PlayerBuilder().firstNameArea("Ken").lastName("Johnston").type(Type.CONTRACTED).rank(1).addAPart(Part.VIOLIN1));
        Player sls = new Player(new PlayerBuilder().firstNameArea("Sandro").lastName("Leal-Santiesteban").type(Type.CONTRACTED).rank(2).addAPart(Part.VIOLIN1));
        Player jh = new Player(new PlayerBuilder().firstNameArea("Joshua").lastName("Huang").type(Type.CONTRACTED).rank(3).addAPart(Part.VIOLIN1));
        Player melissaH = new Player(new PlayerBuilder().firstNameArea("Melissa").lastName("Hernandez").type(Type.CONTRACTED).rank(4).addAPart(Part.VIOLIN1));
        Player ah = new Player(new PlayerBuilder().firstNameArea("Alexander").lastName("Hettinga").type(Type.CONTRACTED).rank(5).addAPart(Part.VIOLIN1));
        Player stefS = new Player(new PlayerBuilder().firstNameArea("Stefanie").lastName("Schore").type(Type.CONTRACTED).rank(6).addAPart(Part.VIOLIN1));
        Player yk = new Player(new PlayerBuilder().firstNameArea("Yejee").lastName("Kim").type(Type.CONTRACTED).rank(7).addAPart(Part.VIOLIN1));
        Player mp = new Player(new PlayerBuilder().firstNameArea("Maura").lastName("Pelinsky").type(Type.CONTRACTED).rank(8).addAPart(Part.VIOLIN1));
        Player jc = new Player(new PlayerBuilder().firstNameArea("Janice").lastName("Carlson").type(Type.CONTRACTED).rank(9).addAPart(Part.VIOLIN1));
        Player jm = new Player(new PlayerBuilder().firstNameArea("Jonathan").lastName("Moser").type(Type.CONTRACTED).rank(1).addAPart(Part.VIOLIN2));
        Player jenJ = new Player(new PlayerBuilder().firstNameArea("Jennifer").lastName("Jansen").type(Type.CONTRACTED).rank(3).addAPart(Part.VIOLIN2));
        Player tobias = new Player(new PlayerBuilder().firstNameArea("Tobias").lastName("Chisnall").type(Type.CONTRACTED).rank(4).addAPart(Part.VIOLIN2));
        Player jiyeonY = new Player(new PlayerBuilder().firstNameArea("Jiyeon").lastName("Yeo").type(Type.CONTRACTED).rank(5).addAPart(Part.VIOLIN2));
        Player cv = new Player(new PlayerBuilder().firstNameArea("Colleen").lastName("Vanderzyden").type(Type.CONTRACTED).rank(6).addAPart(Part.VIOLIN2));
        Player kf = new Player(new PlayerBuilder().firstNameArea("Karen").lastName("Ferren").type(Type.CONTRACTED).rank(7).addAPart(Part.VIOLIN2));
        Player hl = new Player(new PlayerBuilder().firstNameArea("Howard").lastName("Lyon").type(Type.CONTRACTED).rank(8).addAPart(Part.VIOLIN2));
        Player ee = new Player(new PlayerBuilder().firstNameArea("Emilie").lastName("Engel").type(Type.CONTRACTED).rank(9).addAPart(Part.VIOLIN2));
        Player jiYoung = new Player(new PlayerBuilder().firstNameArea("Ji Young").lastName("Nam").type(Type.CONTRACTED).rank(1).addAPart(Part.VIOLA));
        Player sy = new Player(new PlayerBuilder().firstNameArea("Si").lastName("Yu").type(Type.CONTRACTED).rank(2).addAPart(Part.VIOLA));
        Player benS = new Player(new PlayerBuilder().firstNameArea("Benjamin").lastName("Schantz").type(Type.CONTRACTED).rank(3).addAPart(Part.VIOLA));
        Player eriS = new Player(new PlayerBuilder().firstNameArea("Eri").lastName("Snowden-Rodriguez").type(Type.CONTRACTED).rank(1).addAPart(Part.CELLO));
        Player wt = new Player(new PlayerBuilder().firstNameArea("Will").lastName("Teegarden").type(Type.CONTRACTED).rank(2).addAPart(Part.CELLO));
        Player nadineS = new Player(new PlayerBuilder().firstNameArea("Nadine").lastName("Sherman").type(Type.CONTRACTED).rank(3).addAPart(Part.CELLO));
        Player jeffS = new Player(new PlayerBuilder().firstNameArea("Jeff").lastName("Singler").type(Type.CONTRACTED).rank(4).addAPart(Part.CELLO));
        Player bn = new Player(new PlayerBuilder().firstNameArea("Robert").lastName("Nicholson").type(Type.CONTRACTED).rank(5).addAPart(Part.CELLO));
        Player jv = new Player(new PlayerBuilder().firstNameArea("Jean").lastName("Verdecchia").type(Type.CONTRACTED).rank(6).addAPart(Part.CELLO));
        Player kieranH = new Player(new PlayerBuilder().firstNameArea("Kieran").lastName("Hanlon").type(Type.CONTRACTED).rank(1).addAPart(Part.BASS));
        Player josephH = new Player(new PlayerBuilder().firstNameArea("Joseph").lastName("Hernandez").type(Type.CONTRACTED).rank(2).addAPart(Part.BASS));
        Player tomC = new Player(new PlayerBuilder().firstNameArea("Thomas").lastName("Christopherson").type(Type.CONTRACTED).rank(3).addAPart(Part.BASS));
        Player jamesM = new Player(new PlayerBuilder().firstNameArea("James").lastName("Mohney").type(Type.CONTRACTED).rank(4).addAPart(Part.BASS));
        Player mariaP = new Player(new PlayerBuilder().firstNameArea("Maria").lastName("Park").type(Type.SUB).rank(1).addAPart(Part.VIOLIN1));
        Player mikeChen = new Player(new PlayerBuilder().firstNameArea("Mike").lastName("Chen").type(Type.SUB).rank(1).addAPart(Part.VIOLIN1));
        Player dianaV = new Player(new PlayerBuilder().firstNameArea("Diana").lastName("Pepelea").type(Type.SUB).rank(1).addAPart(Part.VIOLIN1));
        Player samPetrey = new Player(new PlayerBuilder().firstNameArea("Sam").lastName("Petrey").type(Type.SUB).rank(1).addAPart(Part.VIOLIN1));
        Player maijaAnstine = new Player(new PlayerBuilder().firstNameArea("Maija").lastName("Anstine").type(Type.SUB).rank(1).addAPart(Part.VIOLA));
        Player chrisBlaha = new Player(new PlayerBuilder().firstNameArea("Chris").lastName("Blaha").type(Type.SUB).rank(1).addAPart(Part.TUBA));
        Player erikSundet = new Player(new PlayerBuilder().firstNameArea("Erik").lastName("Sundet").type(Type.SUB).rank(1).addAPart(Part.TRUMPET));

        playerRepo.saveAll(Arrays.asList(leAnne, seanG, ami, sarahH, danna, heatherS, sarahH, benC, db, kdo, jenJ, jeffS, jiYoung, lk, lel, cr, ma, es, ba, bs, gd,
                da, mh, wc, sb, kh, bradA, ml, mr, kj, sls, jh, melissaH, ah, stefS, yk, mp, jc, sy, benS,
                eriS, ee, jiYoung, cv, kf, hl, wt, tobias, jiyeonY, jenJ, mp, jc, jm, nadineS, bn, jv, kieranH, josephH, tomC, jamesM, mariaP, mikeChen, dianaV,
                samPetrey, maijaAnstine, chrisBlaha, erikSundet));

        Show firstShow = new ShowBuilder().build();
        showRepo.save(firstShow);

        Piece firebird = new PieceBuilder().build();
        pieceRepo.save(firebird);

        ShowPiece birdOnFirst = new ShowPiece(firebird, firstShow);
        showPieceRepo.save(birdOnFirst);

        List<Part> firstPartsList = new ArrayList<>();
        firstPartsList.add(Part.CLARINET);
        firstPartsList.add(Part.EBCLARINET);
        Chair firstChair = new ChairBuilder().parts(firstPartsList).rank(3).build();
        chairRepo.save(firstChair);

        List<Part> trumpetPartsList = new ArrayList<>();
        trumpetPartsList.add(Part.TRUMPET);
        Chair trumpetChair = new ChairBuilder().parts(trumpetPartsList).rank(6).build();
        chairRepo.save(trumpetChair);

        Chair trumpetChair2 = new ChairBuilder().parts(trumpetPartsList).rank(7).build();
        chairRepo.save(trumpetChair2);

        PlayerInChair firstPlayerChair = new PlayerInChair(birdOnFirst, firstChair);
        PlayerInChair secondTrumpetPlayer = new PlayerInChair(birdOnFirst, trumpetChair2);
        secondTrumpetPlayer.setPlayer(gd);
        picRepo.save(firstPlayerChair);
        picRepo.save(secondTrumpetPlayer);

        Collection<Player> playersToSend = new ArrayList<>();

        HashMap<Player, Boolean> eligiblePlayers = new HashMap<>();
        for (Player player : playerRepo.findAllByType(Type.CONTRACTED)) {
            eligiblePlayers.put(player, true);
        }

        for (PlayerInChair picToCheck : picRepo.findAllByShowPiece(firstPlayerChair.getShowPiece())) {
            if (eligiblePlayers.containsKey(picToCheck.getPlayer())) {
                eligiblePlayers.put(picToCheck.getPlayer(), false);
            }
        }

        for (Player player : playerRepo.findAllByType(Type.CONTRACTED)) {
            if (!player.couldSitHere(secondTrumpetPlayer)) {
                eligiblePlayers.put(player, false);
            }
        }

        for (Map.Entry<Player, Boolean> entry : eligiblePlayers.entrySet()) {
            if (entry.getValue().equals(true)) {
                playersToSend.add(entry.getKey());
            }
        }

        for (Player player : playersToSend) {
            System.out.println(player.getFirstNameArea() + "   " + player.getLastName());
        }


//        assertEquals(playersToSend.size(), 54);
    }

    @Test
    public void mustBeAbleToSortPlayers() {

        Player leAnne = new PlayerBuilder().firstNameArea("Leanne").lastName("Wistrom").type(Type.CONTRACTED).rank(1).addAPart(Part.FLUTE).build();
        Player seanG = new PlayerBuilder().firstNameArea("Sean").lastName("Gabriel").type(Type.CONTRACTED).rank(2).addAPart(Part.FLUTE).build();
        Player danna = new PlayerBuilder().firstNameArea("Danna").lastName("Sundet").type(Type.CONTRACTED).rank(1).addAPart(Part.OBOE).build();
        Player heatherS = new Player(new PlayerBuilder().firstNameArea("Heather").lastName("Story").type(Type.CONTRACTED).rank(2).addAPart(Part.OBOE));
        Player sarahH = new Player(new PlayerBuilder().firstNameArea("Sarah").lastName("Hamilton").type(Type.CONTRACTED).rank(3).addAPart(Part.OBOE).addAPart(Part.ENGLISHHORN));
//        Player ami = new Player(new PlayerBuilder().firstNameArea("Ami").lastName("Vardi").type(Type.CONTRACTED).rank(1).addAPart(Part.CLARINET));
//        Player benC = new Player(new PlayerBuilder().firstNameArea("Benjamin").lastName("Chen").type(Type.CONTRACTED).rank(1).addAPart(Part.CLARINET));
//        Player db = new Player(new PlayerBuilder().firstNameArea("David").lastName("Boutin-Bourque").type(Type.CONTRACTED).rank(3).addAPart(Part.CLARINET).addAPart(Part.BASSCLARINET));
//        Player kdo = new Player(new PlayerBuilder().firstNameArea("KeriAnn").lastName("DiBari-Oberle").type(Type.CONTRACTED).rank(4).addAPart(Part.CLARINET).addAPart(Part.EBCLARINET));
//        Player lk = new Player(new PlayerBuilder().firstNameArea("Laura").lastName("Koepke").type(Type.CONTRACTED).rank(1).addAPart(Part.BASSOON));
//        Player lel = new Player(new PlayerBuilder().firstNameArea("Sarah Elizabeth").lastName("Lee").type(Type.CONTRACTED).rank(2).addAPart(Part.BASSOON));
//        Player cr = new Player(new PlayerBuilder().firstNameArea("Chris").lastName("Rapier").type(Type.CONTRACTED).rank(1).addAPart(Part.HORN));
//        Player ma = new Player(new PlayerBuilder().firstNameArea("Mark").lastName("Addleman").type(Type.CONTRACTED).rank(2).addAPart(Part.HORN));
//        Player es = new Player(new PlayerBuilder().firstNameArea("Emily").lastName("Shelley").type(Type.CONTRACTED).rank(3).addAPart(Part.HORN));
//        Player ba = new Player(new PlayerBuilder().firstNameArea("Bryan").lastName("Adkins").type(Type.CONTRACTED).rank(4).addAPart(Part.HORN));
//        Player bs = new Player(new PlayerBuilder().firstNameArea("Benjamin").lastName("Strecker").type(Type.CONTRACTED).rank(5).addAPart(Part.HORN));
////        Player gd = new Player(new PlayerBuilder().firstNameArea("Gary").lastName("Davis").type(Type.CONTRACTED).rank(1).addAPart(Part.TRUMPET));
//        Player da = new Player(new PlayerBuilder().firstNameArea("Douglas").lastName("Amos").type(Type.CONTRACTED).rank(2).addAPart(Part.TRUMPET));
//        Player mh = new Player(new PlayerBuilder().firstNameArea("Micah").lastName("Holt").type(Type.CONTRACTED).rank(3).addAPart(Part.TRUMPET));
//        Player wc = new Player(new PlayerBuilder().firstNameArea("Whitney").lastName("Claire").type(Type.CONTRACTED).rank(1).addAPart(Part.TROMBONE));
//        Player sb = new Player(new PlayerBuilder().firstNameArea("Sean").lastName("Bessette").type(Type.CONTRACTED).rank(1).addAPart(Part.BASSTROMBONE));
//        Player kh = new Player(new PlayerBuilder().firstNameArea("Ken").lastName("Heinlein").type(Type.CONTRACTED).rank(1).addAPart(Part.TUBA));
//        Player bradA = new Player(new PlayerBuilder().firstNameArea("Brad").lastName("Amidon").type(Type.CONTRACTED).rank(1).addAPart(Part.PERCUSSION));
//        Player ml = new Player(new PlayerBuilder().firstNameArea("Matt").lastName("Larson").type(Type.CONTRACTED).rank(2).addAPart(Part.PERCUSSION));
//        Player mr = new Player(new PlayerBuilder().firstNameArea("Melody").lastName("Rapier").type(Type.CONTRACTED).rank(1).addAPart(Part.HARP));
//        Player kj = new Player(new PlayerBuilder().firstNameArea("Ken").lastName("Johnston").type(Type.CONTRACTED).rank(1).addAPart(Part.VIOLIN1));
//        Player sls = new Player(new PlayerBuilder().firstNameArea("Sandro").lastName("Leal-Santiesteban").type(Type.CONTRACTED).rank(2).addAPart(Part.VIOLIN1));
//        Player jh = new Player(new PlayerBuilder().firstNameArea("Joshua").lastName("Huang").type(Type.CONTRACTED).rank(3).addAPart(Part.VIOLIN1));
//        Player melissaH = new Player(new PlayerBuilder().firstNameArea("Melissa").lastName("Hernandez").type(Type.CONTRACTED).rank(4).addAPart(Part.VIOLIN1));
//        Play
        playerRepo.saveAll(Arrays.asList(leAnne, seanG, sarahH, danna, heatherS, sarahH
        ));


        List<Player> playersToSort = new ArrayList<>();
        for (Player player : playerRepo.findAllByType(Type.CONTRACTED)) {
            playersToSort.add(player);
        }
        Collections.sort(playersToSort);

        for (Player player : playersToSort) {
            System.out.println(player.getLastName());
            System.out.println(player.getPrimaryPart());
            System.out.println(player.getRank());
        }
    }


    @Test
    public void shouldBeAbleToSortStringPICs() {

        Show firstShow = new ShowBuilder().build();
        showRepo.save(firstShow);

        Piece firebird = new PieceBuilder().build();
        pieceRepo.save(firebird);

        ShowPiece firebirdOnFirst = new ShowPiece(firebird, firstShow);
        showPieceRepo.save(firebirdOnFirst);

        for (int j = 1; j < 8; j++) {
            chairRepo.save(new ChairBuilder().primaryPart(Part.VIOLIN1).rank(1).build());
        }

        Collection<Chair> chairsClump = (Collection<Chair>) chairRepo.findAll();
        ArrayList<Chair> chairs = new ArrayList<>(chairsClump);

        for (int j = 1; j < chairs.size(); j++) {
            picRepo.save(new PlayerInChair(firebirdOnFirst, chairs.get(j), j));
        }

        List<PlayerInChair> picsToSort = (List<PlayerInChair>) picRepo.findAll();
        Collections.sort(picsToSort);

        for (PlayerInChair pic : picsToSort) {
            System.out.println(pic.getSectionSeat() + "    " + pic.getChair().getRank());
        }
    }

    @Test
    public void tryingOutArrayListReplacing() {

        ArrayList<String> aList = new ArrayList<String>();
        aList.add(0, "1");
        aList.add(1, "2");
        aList.add(2, "3");
        aList.add(3, "4");
        aList.add(4, "5");
        aList.add(5, "Assistant");

        System.out.println(aList);

        String assString = aList.get(5);
        aList.remove(2);
        aList.add(1, assString);
        System.out.println(aList);
    }

    @Test
    public void nowSortingHorns() {
        Show firstShow = new ShowBuilder().build();
        showRepo.save(firstShow);

        Piece firebird = new PieceBuilder().build();
        pieceRepo.save(firebird);

        ShowPiece firebirdOnFirst = new ShowPiece(firebird, firstShow);
        showPieceRepo.save(firebirdOnFirst);

        for (int j = 1; j <= 8; j++) {
            if (j == 3) {
                chairRepo.save(new ChairBuilder().primaryPart(Part.HORN).rank(j).specialDesignate("A").build());
            } else {
                chairRepo.save(new ChairBuilder().primaryPart(Part.HORN).rank(j).build());
            }
        }
        for (Chair chair : chairRepo.findAll()) {
            picRepo.save(new PlayerInChair(firebirdOnFirst, chair));
        }

        System.out.println("first list:   ");
        for (PlayerInChair pic : picRepo.findAll()) {
            if (pic.getChair().getSpecialDesignate() != null) {
                System.out.println(pic.getChair().getPrimaryPart() + "   " + pic.getChair().getSpecialDesignate());
            } else {
                System.out.println(pic.getChair().getPrimaryPart() + "    " + pic.getChair().getRank());
            }
        }

        ArrayList<PlayerInChair> pics = new ArrayList<PlayerInChair>((Collection<? extends PlayerInChair>) picRepo.findAll());

        int assistantIndex = 0;
        int principalIndex = 0;
        boolean assistantExists = false;
        for (PlayerInChair pic : pics) {
            if (pic.getChair().isPrincipalHorn()) {
                principalIndex = pics.indexOf(pic);
            }
            if (pic.getChair().hasAssDesignate()) {
                assistantIndex = pics.indexOf(pic);
                assistantExists = true;
            }
        }

        if (assistantExists) {
            pics.add(principalIndex + 1, pics.get(assistantIndex));
            pics.remove(assistantIndex + 1);
        }


        System.out.println("second list:   ");
        for (PlayerInChair pic : pics) {
            if (pic.getChair().getSpecialDesignate() != null) {
                System.out.println(pic.getChair().getPrimaryPart() + "   " + pic.getChair().getSpecialDesignate());
            } else {
                System.out.println(pic.getChair().getPrimaryPart() + "    " + pic.getChair().getRank());
            }
        }


    }

}
