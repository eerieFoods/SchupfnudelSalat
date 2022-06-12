package com.github.eeriefoods.snsserver.shared.api;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;

public class StudentIdGenerator implements IdentifierGenerator {

    public static final String GENERATOR_NAME = "studentIdGenerator";

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        return ThreadLocalRandom.current().nextLong(100000, 900000);
    }
}
