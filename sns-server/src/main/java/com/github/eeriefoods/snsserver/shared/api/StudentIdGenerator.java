package com.github.eeriefoods.snsserver.shared.api;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class StudentIdGenerator {

    public Long generateStudentId() {
        return ThreadLocalRandom.current().nextLong(100000, 900000);
    }

}
