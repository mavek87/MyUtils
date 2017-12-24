package com.matteoveroni.myutils;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

public class UniqueRandomNumbersGeneratorTest {

	private UniqueRandomNumbersGenerator generator;
	private final ArrayList<Integer> listOfExtractedNumbers = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		listOfExtractedNumbers.clear();
	}

	@Test(expected = IllegalArgumentException.class)
	public void test_ExtractNext_usingNegativeRangeDimension_shouldThrowIllegalArgumentException() throws IllegalArgumentException {
		IntRange range = new IntRange(0, -1);
	}

	@Test
	public void test_ExtractNext_usingRangeWithDimensionOne_extractTheOnlyPossibleNumber() throws UniqueRandomNumbersGenerator.NoMoreUniqueRandNumberExtractableException {
		IntRange range = new IntRange(0, 0);
		assertEquals(1, range.getDimension());

		generator = new UniqueRandomNumbersGenerator(range);
		assertEquals("in a range with dimension one extractNext should extract the only possible number", 0, generator.extractNext());
	}

	@Test
	public void test_numberOfMaxPossibleExtractions() throws UniqueRandomNumbersGenerator.NoMoreUniqueRandNumberExtractableException {
		int minNumber = 0;
		int maxNumber = 10;
		IntRange range = new IntRange(minNumber, maxNumber);
		int expectedNumberOfPossibleExtractions = maxNumber + 1;

		generator = new UniqueRandomNumbersGenerator(range);

		try {
			do {
				// Extract unique rand numbers untill it's possible
				int extractedNumber = generator.extractNext();
				listOfExtractedNumbers.add(extractedNumber);
			} while (true);
		} catch (UniqueRandomNumbersGenerator.NoMoreUniqueRandNumberExtractableException ex) {
			System.out.println("test_numberOfMaxPossibleExtractions => " + Arrays.toString(listOfExtractedNumbers.toArray()));
			assertEquals(expectedNumberOfPossibleExtractions, listOfExtractedNumbers.size());
		}
	}

	@Test
	public void test_restrictedMaxNumberOfExtractions() throws UniqueRandomNumbersGenerator.NoMoreUniqueRandNumberExtractableException {
		int minNumber = 0;
		int maxNumber = 10;
		IntRange range = new IntRange(minNumber, maxNumber);
		int maxNumberOfExtractions = 3;

		generator = new UniqueRandomNumbersGenerator(range, maxNumberOfExtractions);

		try {
			do {
				// Extract unique rand numbers untill it's possible
				int extractedNumber = generator.extractNext();
				listOfExtractedNumbers.add(extractedNumber);
			} while (true);
		} catch (UniqueRandomNumbersGenerator.NoMoreUniqueRandNumberExtractableException ex) {
			System.out.println("testRestrictedMaxNumberOfExtractions => " + Arrays.toString(listOfExtractedNumbers.toArray()));
			assertEquals("testRestrictedMaxNumberOfExtractions", maxNumberOfExtractions, listOfExtractedNumbers.size());
		}
	}
}
