package com.github.eeriefoods.snsserver.course.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.InputMismatchException;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StudentNotMemberOfCourseException extends InputMismatchException {

    public StudentNotMemberOfCourseException(String kursId, long matrikelNummer) {
        super("Der Studi mit der MatrikelNummer %d ist nicht im Kurs mit der Id %s".formatted(matrikelNummer, kursId));
    }

}
