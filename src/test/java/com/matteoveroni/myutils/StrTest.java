package com.matteoveroni.myutils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Matteo Veroni
 */
public class StrTest {

    private static final int NUMBER_OF_TESTS_REPETITIONS = 100000;

    private static final String ONLY_LETTERS_STRING = "AagGzZ";
    private static final String ONLY_LOWERCASE_LETTERS_STRING = "agz";
    private static final String ONLY_DIGIT_STRING = "1234";
    private static final String MIXED_STRING = "Aa1;b23^?4zZ";

    private Range randomNumberOfLettersRange;

    @Test
    public void test_isStringFormedByOnlyLetters_Return_False_IfItsFalse_OrTrueIfItIs() {
        assertTrue(Str.isStringFormedByOnlyLetters(ONLY_LETTERS_STRING));
        assertTrue(Str.isStringFormedByOnlyLetters(ONLY_LOWERCASE_LETTERS_STRING));
        assertFalse(Str.isStringFormedByOnlyLetters(ONLY_DIGIT_STRING));
        assertFalse(Str.isStringFormedByOnlyLetters(MIXED_STRING));
    }

    @Test
    public void test_isStringFormedByOnlyLowercaseLetters_Return_False_IfItsFalse_OrTrueIfItIs() {
        assertFalse(Str.isStringFormedByOnlyLowercaseLetters(ONLY_LETTERS_STRING));
        assertTrue(Str.isStringFormedByOnlyLowercaseLetters(ONLY_LOWERCASE_LETTERS_STRING));
        assertFalse(Str.isStringFormedByOnlyLowercaseLetters(ONLY_DIGIT_STRING));
        assertFalse(Str.isStringFormedByOnlyLowercaseLetters(MIXED_STRING));
    }

    @Test
    public void test_isStringFormedByOnlyDigits_Return_False_IfItsFalse_OrTrueIfItIs() {
        assertFalse(Str.isStringFormedByOnlyDigits(ONLY_LETTERS_STRING));
        assertFalse(Str.isStringFormedByOnlyDigits(ONLY_LOWERCASE_LETTERS_STRING));
        assertTrue(Str.isStringFormedByOnlyDigits(ONLY_DIGIT_STRING));
        assertFalse(Str.isStringFormedByOnlyDigits(MIXED_STRING));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_generateRndString_Using_LowRangeBound_GreaterThan_HiRangeBound_ThrowsIllegalArgumentException() {
        int lowBound = 2;
        int hiBound = 1;

        randomNumberOfLettersRange = new Range(lowBound, hiBound);
        Str.generateRndLowercaseString(randomNumberOfLettersRange);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_generateRndString_Using_LowRangeBound_SmallerThan_HiRangeBound_ThrowsIllegalArgumentException() {
        int hiBound = 2;
        int lowBound = hiBound;

        randomNumberOfLettersRange = new Range(lowBound, hiBound);
        Str.generateRndLowercaseString(randomNumberOfLettersRange);
    }

    @Test
    public void test_generateRndLowercaseString_Using_LowRangeBound_SmallerThan_HiRangeBound_ReturnsRandomString() {
        int lowBound = 1;
        int hiBound = 5;

        randomNumberOfLettersRange = new Range(lowBound, hiBound);

        for (int i = 0; i < NUMBER_OF_TESTS_REPETITIONS; i++) {
            String generatedString = Str.generateRndLowercaseString(randomNumberOfLettersRange);

            assertTrue("genereted string lenght\'s should be greater than range low bound", 
                    generatedString.length() >= lowBound
            );
            
            assertTrue("genereted string lenght\'s should be minor than range hi bound", 
                    generatedString.length() <= hiBound
            );

            assertTrue("generated string should be only formed by all lowercase chars", 
                    Str.isStringFormedByOnlyLowercaseLetters(generatedString)
            );
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_generateRndString_Using_FixedNumberOfLetters_SmallerThan_Zero_ThrowsIllegalArgumentException() {
        int numberOfLetters = -1;

        Str.generateRndLowercaseString(numberOfLetters);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_generateRndString_Using_FixedNumberOfLetters_EqualTo_Zero_ThrowsIllegalArgumentException() {
        int numberOfLetters = 0;

        Str.generateRndLowercaseString(numberOfLetters);
    }

    @Test
    public void test_generateRndLowercaseString_Using_FixedNumberOfLetters_GreaterThan_Zero_generatesStringWithExpectedNumberOfLetters() {
        generateRndLowercaseStringWithSpecifiedLetterWorks(1);
        generateRndLowercaseStringWithSpecifiedLetterWorks(5);
        generateRndLowercaseStringWithSpecifiedLetterWorks(3);
    }

    private void generateRndLowercaseStringWithSpecifiedLetterWorks(int numberOfLetters) {
        String generatedString = Str.generateRndLowercaseString(numberOfLetters);
        assertTrue("generated string length should be equal to requested number of letters",
                generatedString.length() == numberOfLetters
        );
    }
}
