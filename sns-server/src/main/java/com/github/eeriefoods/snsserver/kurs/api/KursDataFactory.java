package com.github.eeriefoods.snsserver.kurs.api;

import com.github.eeriefoods.snsserver.kurs.domain.Kurs;
import org.springframework.stereotype.Component;

@Component
public class KursDataFactory {

    public KursData from(Kurs kurs) {
        KursData data = new KursData();

        data.setId(kurs.getId());
        data.setMembers(kurs.getMembers());
        data.setRoom(kurs.getRoom());

        return data;
    }

}
