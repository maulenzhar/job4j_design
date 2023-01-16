package ru.job4j.io.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        boolean isActive = false;
        byte b = 3;
        short s = 3;
        long l = 5;
        double d = 8.5;
        float f = 8.5F;
        char c = 102;
        LOG.debug("User info name : {}, age : {}, isActive : {}, b : {}, s : {}, l : {}, d : {}, f : {}, c : {}", name, age, isActive, b, s, l, d, f, c);
    }
}