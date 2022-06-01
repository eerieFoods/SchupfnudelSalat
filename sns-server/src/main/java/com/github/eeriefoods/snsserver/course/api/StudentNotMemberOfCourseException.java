package com.github.eeriefoods.snsserver.course.api;

import java.util.InputMismatchException;

public class StudentNotMemberOfCourseException extends InputMismatchException {

    public StudentNotMemberOfCourseException(String kursId, long matrikelNummer) {
        super("Der Studi mit der MatrikelNummer %d ist nicht im Kurs mit der Id %s".formatted(matrikelNummer, kursId));
    }

}
