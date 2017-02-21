package com.matteoveroni.myutils;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * @author Matteo Veroni
 */
public final class Str {

    public static final int FIRST_ASCII_CHAR_CODE = 97;
    public static final int NUMBER_OF_ASCII_CHAR_CODES = 25;

    private static final Random RANDOM_GENERATOR = new SecureRandom();

    private static volatile List<String> uniqueRandomStringsGenerated = new ArrayList<>();

    public static final String concat(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        concatStrings(strings, stringBuilder);
        return stringBuilder.toString();
    }

    public static final String concat(Collection<String> strings) {
        final StringBuilder stringBuilder = new StringBuilder();
        concatStrings(strings.toArray(new String[strings.size()]), stringBuilder);
        return stringBuilder.toString();
    }

    private static StringBuilder concatStrings(String[] strings, StringBuilder stringBuilder) {
        for (String s : strings) {
            stringBuilder.append(s);
        }
        return stringBuilder;
    }

    public synchronized static final String generateUniqueRndString(Range numberOfLettersRange) throws IllegalArgumentException {
        ifRangeInvalidThrowIllegalArgumentException(numberOfLettersRange);
        String generatedString;
        do {
            generatedString = generateRndString(numberOfLettersRange);
        } while (uniqueRandomStringsGenerated.contains(generatedString));
        uniqueRandomStringsGenerated.add(generatedString);

        return generatedString;
    }

    public static final String generateRndString(int numberOfLetters) throws IllegalArgumentException {
        ifNumberOfLettersInvalidThrowIllegalArgumentException(numberOfLetters);

        StringBuilder randomString = new StringBuilder();

        for (int letterIndex = 0; letterIndex < numberOfLetters; letterIndex++) {
            char randomChar = (char) (FIRST_ASCII_CHAR_CODE + RANDOM_GENERATOR.nextInt(NUMBER_OF_ASCII_CHAR_CODES));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    public static final String generateRndString(Range numberOfLettersRange) throws IllegalArgumentException {
        ifRangeInvalidThrowIllegalArgumentException(numberOfLettersRange);

        int generatedNumberOfLetters = generateRandomNumberOfLetters(numberOfLettersRange);
        return generateRndString(generatedNumberOfLetters);
    }

    private static int generateRandomNumberOfLetters(Range numberOfLettersRange) {
        int minNumberOfLetters = numberOfLettersRange.getLowBorder();
        int maxNumberOfLetters = numberOfLettersRange.getHighBorder();

        // MIN_NUM_LETTERS <= numbersOfLetters <= MAX_NUM_LETTERS
        return (RANDOM_GENERATOR.nextInt(maxNumberOfLetters - minNumberOfLetters)) + minNumberOfLetters;
    }

    private static void ifRangeInvalidThrowIllegalArgumentException(Range numberOfLettersRange) throws IllegalArgumentException {
        int minNumberOfLetters = numberOfLettersRange.getLowBorder();
        int maxNumberOfLetters = numberOfLettersRange.getHighBorder();
        if ((minNumberOfLetters < maxNumberOfLetters) && (minNumberOfLetters > 0) && (maxNumberOfLetters > 0)) {
            throw new IllegalArgumentException("(minNumberOfLetters - maxNumberOfLetters) range invalid");
        }
    }

    private static void ifNumberOfLettersInvalidThrowIllegalArgumentException(int numberOfLetters) throws IllegalArgumentException {
        if (numberOfLetters < 1) {
            throw new IllegalArgumentException("number of letters invalid. They must be greater than 0");
        }
    }
}
