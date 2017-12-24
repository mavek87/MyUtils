package com.matteoveroni.myutils;

import java.util.HashSet;
import java.util.Set;

public class UniqueRandomNumbersGenerator {

    private final Set<Integer> extractedUniqueRandNumbers = new HashSet<>();
    private final int maxNumberOfExtractions;
    private final IntRange rangeOfExtractableIntegers;

    public UniqueRandomNumbersGenerator(IntRange range) {
        rangeOfExtractableIntegers = range;
        this.maxNumberOfExtractions = rangeOfExtractableIntegers.getDimension();
    }

    public UniqueRandomNumbersGenerator(IntRange range, int maxNumberOfExtractions) {
        rangeOfExtractableIntegers = range;
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

    public class NoMoreUniqueRandNumberExtractableException extends Exception {
    }
}
