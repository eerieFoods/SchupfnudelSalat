package com.github.eeriefoods.snsserver.kurs.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class KursNotFoundException extends NoSuchElementException {

    public KursNotFoundException(String kursId) {
        super("Kurs mit ID %s nicht gefunden!".formatted(kursId));
    }

}
