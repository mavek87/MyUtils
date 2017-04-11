package com.matteoveroni.myutils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author Matteo Veroni
 */
public class Int {

    private static final Random RAND_GENERATOR = new SecureRandom();

    public static int getRandomInteger(int min, int max) throws IllegalArgumentException {
        if (min < 0 || max < 0 || min > max) {
            throw new IllegalArgumentException("min number is greater than max number");
        }

        return RAND_GENERATOR.nextInt((max - min) + 1) + min;
    }

    public static int getRandomInteger(IntRange range) {
        return getRandomInteger(range.getLowBorder(), range.getHighBorder());
    }
}
