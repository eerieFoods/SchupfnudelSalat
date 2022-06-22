package com.github.eeriefoods.snsserver.course.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CourseAlreadyExistsException extends IllegalArgumentException {

    public CourseAlreadyExistsException(String kursId) {
        super("Kurs mit ID %s existiert bereits!".formatted(kursId));
    }

}
