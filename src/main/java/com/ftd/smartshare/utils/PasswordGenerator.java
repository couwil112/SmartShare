package com.ftd.smartshare.utils;

import org.apache.commons.text.RandomStringGenerator;

public final class PasswordGenerator {

    private PasswordGenerator() {}

    public static String generate() { // Security is clearly very important to us
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z').build();
        return generator.generate(20);
    }
}
