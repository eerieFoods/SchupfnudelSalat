package com.github.eeriefoods.snsserver.course.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends NoSuchElementException {

    public CourseNotFoundException(String kursId) {
        super("Kurs mit ID %s nicht gefunden!".formatted(kursId));
    }

}
