package com.github.eeriefoods.snsserver.student.api;

import java.util.NoSuchElementException;

public class StudentNotFoundException extends NoSuchElementException {

    public StudentNotFoundException(int matrikelNummer) {
        super("Studi mit Matrikelnummer %d nicht gefunden".formatted(matrikelNummer));
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

}
