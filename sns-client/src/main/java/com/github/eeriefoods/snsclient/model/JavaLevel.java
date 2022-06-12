package com.github.eeriefoods.snsclient.model;

public enum JavaLevel {

    SEHR_GUT(1),
    GUT(2),
    BEFRIEDIGEND(3),
    AUSREICHEND(4),
    MANGELHAFT(5),
    UNGENUEGEND(6);

    private final int note;

    JavaLevel(int note) {
        this.note = note;
    }

}
