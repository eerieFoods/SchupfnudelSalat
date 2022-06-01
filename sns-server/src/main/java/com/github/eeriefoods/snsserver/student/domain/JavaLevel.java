package com.github.eeriefoods.snsserver.student.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum JavaLevel {

    SEHR_GUT(1),
    GUT(2),
    BEFRIEDIGEND(3),
    AUSREICHEND(4),
    MANGELHAFT(5),
    UNGENUEGEND(6);

    private final int note;

}
