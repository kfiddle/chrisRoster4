package com.example.demo.enums;

import java.util.Arrays;

public enum Part {

    PICCOLO("Piccolo"),
    FLUTE("Flute"),
    ALTOFLUTE("Alto Flute"),
    OBOE("Oboe"),
    ENGLISHHORN("English Horn"),
    CLARINET("Clarinet"),
    ACLARINET("A Clarinet"),
    CCLARINET("C Clarinet"),
    EBCLARINET("Eb Clarinet"),
    BASSCLARINET("Bass Clarinet"),
    SAX("Sax"),
    BASSOON("Bassoon"),
    CONTRA("Contra"),
    HORN("Horn"),
    WAGNERTUBA("Wagner Tuba"),
    TRUMPET("Trumpet"),
    CORNET("Cornet"),
    FUGALHORN("Fugal Horn"),
    TROMBONE("Trombone"),
    BASSTROMBONE("Bass Trombone"),
    EUPHONIUM("Euphonium"),
    TUBA("Tuba"),
    TIMPANI("Timpani"),
    PERCUSSION("Percussion"),
    HARP("Harp"),
    PIANO("Piano"),
    CELESTE("Celeste"),
    GLOCKENSPIEL("Glockenspiel"),
    ORGAN("Organ"),
    KEYBOARD("Keyboard"),
    VOICES("Voices"),
    VIOLIN1("Violin1"),
    VIOLIN2("Violin2"),
    VIOLA("Viola"),
    CELLO("Cello"),
    BASS("Bass");

    private final String stringVersion;

    Part(String stringVersion) {
        this.stringVersion = stringVersion;
    }

    public String toString() {
        return stringVersion;
    }

    public int getScoreOrder() {
        return Arrays.asList(Part.values()).indexOf(this);
    }

    public int compare(Part otherPart) {
        return Integer.compare(this.getScoreOrder(), otherPart.getScoreOrder());
    }

    static public Part ofPartName(String partName) {
        for (Part part : Part.values()) {
            if (part.toString().equals(partName)) {
                return part;
            }
        }
        throw new IllegalArgumentException("No such part name as: " + partName);
    }

}




