package com.github.eeriefoods.snsserver.student.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class StudentAlreadyExistsException extends IllegalArgumentException {

    public StudentAlreadyExistsException(long matrikelNummer) {
        this("Student:in mit Matrikelnummer %d existiert bereits!".formatted(matrikelNummer));
    }

    public StudentAlreadyExistsException(String message) {
        super(message);
    }

}
