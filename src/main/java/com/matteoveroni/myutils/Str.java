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

    /**
     *
     * Method which concatenates each String passed into a unique string. (eg.
     * Str.concat("Hello", " World!"); returns => "Hello World!".
     *
     * @param strings Strings to concatenate passed to this method
     * @return The concatenation of each string passed to this method
     */
    public static final String concat(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        concatStrings(strings, stringBuilder);
        return stringBuilder.toString();
    }

    /**
     * Method which concatenates a collection of strings into a unique string.
     *
     * @param strings A collection of strings to concatenate into a unique
     * string
     * @return A unique string formed by the concatenation of a collection of
     * strings
     */
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

    /**
     * Generate a random lowercase string with random length between a number of
     * letter range. Each random string generated is also being stored in the
     * RAM to be unique for this session.
     *
     * @param numberOfLettersRange The min and max length of the random string
     * @return a unique random lowercase string with random length
     * @throws IllegalArgumentException If numberOfLettersRange is invalid (eg.
     * lowBoundRange greater or equal than hiBoundRange, or bounds are minor or
     * equal than 0)
     */
    public synchronized static final String generateUniqueRndLowercaseString(Range numberOfLettersRange) throws IllegalArgumentException {
        String generatedString;
        do {
            try {
                generatedString = generateRndLowercaseString(numberOfLettersRange);
            } catch (IllegalArgumentException ex) {
                throw new IllegalArgumentException(ex);
            }
        } while (UNIQUE_RANDOM_STRINGS_GENERATED.contains(generatedString));
        UNIQUE_RANDOM_STRINGS_GENERATED.add(generatedString);

        return generatedString;
    }

    /**
     * Generate a random lowercase string with random length between a number of
     * letter range.
     *
     * @param numberOfLettersRange The min and max length of the random string
     * @return a random lowercase string with random length
     * @throws IllegalArgumentException If numberOfLettersRange is invalid (eg.
     * lowBoundRange greater or equal than hiBoundRange, or bounds are minor or
     * equal than 0)
     */
    public static final String generateRndLowercaseString(Range numberOfLettersRange) throws IllegalArgumentException {
        ifRangeInvalidThrowIllegalArgumentException(numberOfLettersRange);

        int generatedNumberOfLetters = generateRandomNumberOfLetters(numberOfLettersRange);
        return Str.generateRndLowercaseString(generatedNumberOfLetters);
    }

    /**
     * Generate a random lowercase string
     *
     * @param numberOfLetters The fixed number of letter of the string
     * @return a random lowercase string with fixed length
     * @throws IllegalArgumentException If numberOfLetters is invalid (eg. minor
     * or equal than zero)
     */
    public static final String generateRndLowercaseString(int numberOfLetters) throws IllegalArgumentException {
        ifNumberOfLettersInvalidThrowIllegalArgumentException(numberOfLetters);
        StringBuilder randomString = new StringBuilder();

        for (int letterIndex = 0; letterIndex < numberOfLetters; letterIndex++) {
            char randomChar = (char) (FIRST_ASCII_LOWERCASE_LETTER_CODE + RANDOM_GENERATOR.nextInt(NUMBER_OF_ASCII_LOWERCASE_LETTERS));
            randomString.append(randomChar);
        }

        return randomString.toString();
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

    /**
     * Method which capitalize the first character of a string if it is a
     * letter. Otherwise it returns the same string passed.
     *
     * @param string The original string to manipulate
     * @return The original string with first character capitalized (if it was a
     * letter)
     * @throws IllegalArgumentException If the passed string is empty or null
     */
    public static final String capitalizeFirstLetter(String string) throws IllegalArgumentException {
        ifStringNullOrEmptyThrowIllegalArgumentException(string);
        if (Character.isLetter(string.charAt(0))) {
            char firstCharacterOfString = Character.toUpperCase(string.charAt(0));
            String stringAfterFirstCharacter = string.substring(1);

            StringBuilder stringWithFirstLetterCapitalized = new StringBuilder(Character.toString(firstCharacterOfString));
            stringWithFirstLetterCapitalized.append(stringAfterFirstCharacter);
            string = stringWithFirstLetterCapitalized.toString();
        }
        return string;
    }

    /**
     * Remove last letter/character of a string or throw
     * IllegalArgumentException if it is empty or null
     *
     * @param string The original string to manipulate
     * @return The original string without last letter
     * @throws IllegalArgumentException If the passed string is empty or null
     */
    public static final String removeLastLetter(String string) throws IllegalArgumentException {
        ifStringNullOrEmptyThrowIllegalArgumentException(string);
        return string.substring(0, string.length() - 1);
    }

    private static void ifStringNullOrEmptyThrowIllegalArgumentException(String string) throws IllegalArgumentException {
        if (isNullOrEmpty(string)) {
            throw new IllegalArgumentException("argument passed is null or a empty string");
        }
    }

    /**
     * Method which evaluates whether the string is formed by only letters or
     * not.
     *
     * @param string The original string to analyze
     * @return A true or false boolean whether the string is formed by only
     * letters or not
     * @throws IllegalArgumentException If the passed string is empty or null
     */
    public static final boolean isStringFormedByOnlyLetters(String string) throws IllegalArgumentException {
        ifStringNullOrEmptyThrowIllegalArgumentException(string);

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetter(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method which evaluates whether the string is formed by only lowercase
     * letters or not.
     *
     * @param string The original string to analyze
     * @return A true or false boolean whether the string is formed by only
     * lowercase letters or not
     * @throws IllegalArgumentException If the passed string is empty or null
     */
    public static final boolean isStringFormedByOnlyLowercaseLetters(String string) throws IllegalArgumentException {
        ifStringNullOrEmptyThrowIllegalArgumentException(string);

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLowerCase(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method which evaluates whether the string is formed by only digits or
     * not.
     *
     * @param string The original string to analyze
     * @return A true or false boolean whether the string is formed by only
     * digits or not
     * @throws IllegalArgumentException If the passed string is empty or null
     */
    public static final boolean isStringFormedByOnlyDigits(String string) throws IllegalArgumentException {
        ifStringNullOrEmptyThrowIllegalArgumentException(string);

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isDigit(string.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method which evaluates if a string is null/empty or not.
     *
     * @param string The original string to analyze
     * @return A true or false boolean whether the passed string is null/empty
     * or not
     */
    public static final boolean isNullOrEmpty(String string) {
        return (string == null) || (string.trim().isEmpty());
    }

    /**
     * Method which evaluates if a string is NOT null/empty or otherwise.
     *
     * @param string The original string to analyze
     * @return A true or false boolean whether the passed string is NOT
     * null/empty or otherwise
     */
    public static final boolean isNotNullOrEmpty(String string) {
        return !isNullOrEmpty(string);
    }
}
