package com.matteoveroni.myutils;

import java.util.HashSet;
import java.util.Set;

public class UniqueRandomNumbersGenerator {

    private final Set<Integer> extractedUniqueRandNumbers = new HashSet<>();
    private final int maxNumberOfExtractions;
    private final IntRange rangeOfExtractableIntegers;

    public UniqueRandomNumbersGenerator(int minNumber, int maxNumber) {
        rangeOfExtractableIntegers = new IntRange(minNumber, maxNumber);
        checkRangeOfExtractableIntegersDimensionValidity();
        this.maxNumberOfExtractions = rangeOfExtractableIntegers.getDimension();
    }

    public UniqueRandomNumbersGenerator(int minNumber, int maxNumber, int maxNumberOfExtractions) {
        rangeOfExtractableIntegers = new IntRange(minNumber, maxNumber);
        checkRangeOfExtractableIntegersDimensionValidity();
        if (maxNumberOfExtractions < rangeOfExtractableIntegers.getDimension()) {
            this.maxNumberOfExtractions = maxNumberOfExtractions;
        } else {
            this.maxNumberOfExtractions = rangeOfExtractableIntegers.getDimension();
        }
    }

    public int extractNext() throws NoMoreUniqueRandNumberExtractableException {
        int initialSizeOfExtractedNumbers = extractedUniqueRandNumbers.size();
        if (initialSizeOfExtractedNumbers >= maxNumberOfExtractions) {
            throw new NoMoreUniqueRandNumberExtractableException();
        }
        int number;
        do {
            number = Int.getRandomInt(rangeOfExtractableIntegers);
            extractedUniqueRandNumbers.add(number);
        } while (extractedUniqueRandNumbers.size() == initialSizeOfExtractedNumbers);
        return number;
    }

    private void checkRangeOfExtractableIntegersDimensionValidity() {
        if (rangeOfExtractableIntegers.getDimension() < 1) {
            throw new IllegalArgumentException("Range dimension of extractable numbers must be greater than 0");
        }
    }

    public class NoMoreUniqueRandNumberExtractableException extends Exception {
    }
}
