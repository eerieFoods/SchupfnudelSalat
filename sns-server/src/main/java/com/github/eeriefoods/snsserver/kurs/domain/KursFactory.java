package com.github.eeriefoods.snsserver.kurs.domain;

import com.github.eeriefoods.snsserver.kurs.api.KursData;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;

@Component
public class KursFactory {

    public Kurs from(KursData kursData) {
        Kurs kurs = new Kurs();

        kurs.setId(kursData.getId());
        kurs.setMembers(Optional.ofNullable(kursData.getMembers()).orElse(new HashSet<>()));
        kurs.setRoom(kursData.getRoom());

        return kurs;
    }

}
