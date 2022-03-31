package com.example.demo.legos.playerInChair;

import com.example.demo.enums.Part;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerInChairSorter {

    Collection<PlayerInChair> picsToSort;
    List<PlayerInChair> picsToReturn = new ArrayList<>();

    PlayerInChairSorter(Collection<PlayerInChair> picsToSort) {
        this.picsToSort = picsToSort;
    }

//    public void byInstrument(Collection<PlayerInChair> pics) {
//
//    }


//    public List<PlayerInChair> sort() {
//        Collection<Collection<PlayerInChair>> allByInstrument = new ArrayList<>();
//        for (Part part : Part.values()) {
//            Collection<PlayerInChair> bySinglePart = new ArrayList<>();
//            for (PlayerInChair pic : picsToSort) {
//                if (pic.getChair().getPrimaryPart().equals(part)) {
//                    bySinglePart.add(pic);
//                }
//            }
//           allByInstrument.add(bySinglePart);
//        }
//
//        for (Collection<PlayerInChair> byInstrumentList : allByInstrument ) {
//
//        }
//
//        return picsToReturn;
//    }




}
