package com.vaghar.money.transfer.config.generator;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.Random;

public class CustomIdGenerator implements IdentifierGenerator {

    @Override
    public Serializable generate(
            SharedSessionContractImplementor session, Object obj)
            throws HibernateException {
        Random random = new Random();
        int min = 1000000000;
        int max = 1999999999;
        return random.nextInt((max - min + 1) + min);
    }

}