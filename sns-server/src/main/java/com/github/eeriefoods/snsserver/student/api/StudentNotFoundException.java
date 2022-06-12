package com.github.eeriefoods.snsserver.student.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends NoSuchElementException {

    public StudentNotFoundException(long matrikelNummer) {
        this("Studi mit Matrikelnummer %d nicht gefunden".formatted(matrikelNummer));
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

}
