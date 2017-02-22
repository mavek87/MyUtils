package com.matteoveroni.myutils;

import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @author Matteo Veroni
 */
public final class Str {

    public static final int FIRST_ASCII_LOWERCASE_LETTER_CODE = 97;
    public static final int NUMBER_OF_ASCII_LOWERCASE_LETTERS = 25;
    public static final int LAST_ASCII_LOWERCASE_LETTER_CODE
            = FIRST_ASCII_LOWERCASE_LETTER_CODE + NUMBER_OF_ASCII_LOWERCASE_LETTERS;

    private static final Random RANDOM_GENERATOR = new SecureRandom();
    private static final Set<String> UNIQUE_RANDOM_STRINGS_GENERATED = new HashSet<>();

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

    public synchronized static final String generateUniqueRndLowercaseString(Range numberOfLettersRange) throws IllegalArgumentException {
        ifRangeInvalidThrowIllegalArgumentException(numberOfLettersRange);
        String generatedString;
        do {
            generatedString = generateRndLowercaseString(numberOfLettersRange);
        } while (UNIQUE_RANDOM_STRINGS_GENERATED.contains(generatedString));
        UNIQUE_RANDOM_STRINGS_GENERATED.add(generatedString);

        return generatedString;
    }

    public static final String generateRndLowercaseString(int numberOfLetters) throws IllegalArgumentException {
        ifNumberOfLettersInvalidThrowIllegalArgumentException(numberOfLetters);

        StringBuilder randomString = new StringBuilder();

        for (int letterIndex = 0; letterIndex < numberOfLetters; letterIndex++) {
            char randomChar = (char) (FIRST_ASCII_LOWERCASE_LETTER_CODE + RANDOM_GENERATOR.nextInt(NUMBER_OF_ASCII_LOWERCASE_LETTERS));
            randomString.append(randomChar);
        }

        return randomString.toString();
    }

    public static final String generateRndLowercaseString(Range numberOfLettersRange) throws IllegalArgumentException {
        ifRangeInvalidThrowIllegalArgumentException(numberOfLettersRange);

        int generatedNumberOfLetters = generateRandomNumberOfLetters(numberOfLettersRange);
        return Str.generateRndLowercaseString(generatedNumberOfLetters);
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
        if ((minNumberOfLetters > maxNumberOfLetters) || (minNumberOfLetters < 1) || (maxNumberOfLetters < 1)) {
            throw new IllegalArgumentException("(minNumberOfLetters - maxNumberOfLetters) range invalid");
        }
    }

    private static void ifNumberOfLettersInvalidThrowIllegalArgumentException(int numberOfLetters) throws IllegalArgumentException {
        if (numberOfLetters < 1) {
            throw new IllegalArgumentException("number of letters invalid. They must be greater than 0");
        }
    }

    public static final boolean isStringFormedByOnlyLetters(String string) throws IllegalArgumentException {
        ifStringNullOrEmptyThrowIllegalArgumentException(string);

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetter(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isStringFormedByOnlyLowercaseLetters(String string) throws IllegalArgumentException {
        ifStringNullOrEmptyThrowIllegalArgumentException(string);

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLowerCase(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isStringFormedByOnlyDigits(String string) throws IllegalArgumentException {
        ifStringNullOrEmptyThrowIllegalArgumentException(string);

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static void ifStringNullOrEmptyThrowIllegalArgumentException(String string) throws IllegalArgumentException {
        if (isNullOrEmpty(string)) {
            throw new IllegalArgumentException("argument passed is null or a empty string");
        }
    }

    public static final boolean isNullOrEmpty(String string) {
        return (string == null) || (string.trim().isEmpty());
    }

    public static final boolean isNotNullOrEmpty(String string) {
        return !isNullOrEmpty(string);
    }
}
