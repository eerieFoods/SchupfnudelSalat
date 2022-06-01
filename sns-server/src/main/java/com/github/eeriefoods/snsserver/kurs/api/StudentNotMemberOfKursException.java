package com.github.eeriefoods.snsserver.kurs.api;

import java.util.InputMismatchException;

public class StudentNotMemberOfKursException extends InputMismatchException {

    public StudentNotMemberOfKursException(String kursId, long matrikelNummer) {
        super("Der Studi mit der MatrikelNummer %d ist nicht im Kurs mit der Id %s".formatted(matrikelNummer, kursId));
    }

}
