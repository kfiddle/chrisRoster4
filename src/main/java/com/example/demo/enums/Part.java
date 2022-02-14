package com.example.demo.enums;

public enum Part {

    PICCOLO("Piccolo", 1),
    FLUTE("Flute", 2),
    ALTOFLUTE("Alto Flute", 3),
    OBOE("Oboe", 4),
    ENGLISHHORN("English Horn", 5),
    CLARINET("Clarinet", 6),
    EBCLARINET("Eb Clarinet", 7),
    BASSCLARINET("Bass Clarinet", 8),
    SAX("Sax", 9),
    BASSOON("Bassoon", 10),
    CONTRA("Contra", 11),
    HORN("Horn", 12),
    TRUMPET("Trumpet", 13),
    TROMBONE("Trombone", 14),
    BASSTROMBONE("Bass Trombone", 15),
    EUPHONIUM("Euphonium", 16),
    TUBA("Tuba", 17),
    TIMPANI("Timpani", 18),
    PERCUSSION("Percussion", 19),
    HARP("Harp", 20),
    PIANO("Piano", 21),
    KEYBOARD("Keyboard", 22),
    VIOLIN1("Violin1", 23),
    VIOLIN2("Violin2", 24),
    VIOLA("Viola", 25),
    CELLO("Cello", 26),
    BASS("Bass", 27);

    private final String stringVersion;
    private final int scoreOrder;

    Part(String stringVersion, int scoreOrder) {
        this.stringVersion = stringVersion;
        this.scoreOrder = scoreOrder;
    }

    public String toString() {
        return stringVersion;
    }

    public int getScoreOrder() {
        return scoreOrder;
    }

    public int compare(Part otherPart) {
        return Integer.compare(scoreOrder, otherPart.getScoreOrder());
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




