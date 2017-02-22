package com.matteoveroni.myutils;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Matteo Veroni
 */
public class StrTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void test_generateRndString_UsingLowRangeBound_GreaterThan_HiRangeBound_ThrowsIllegalArgumentException() {
        int lowBound = 2;
        int hiBound = 1;
        
        Range range = new Range(lowBound, hiBound);
        Str.generateRndString(range);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void test_generateRndString_UsingLowRangeBound_SmallerThan_HiRangeBound_ThrowsIllegalArgumentException() {
        int hiBound = 2;
        int lowBound = hiBound;
        
        Range range = new Range(lowBound, hiBound);
        Str.generateRndString(range);
    }
    
    public void test_generateRndString_UsingLowRangeBound_SmallerThan_HiRangeBound_ReturnsRandomString() {
        int lowBound = 1;
        int hiBound = 2;
        
        Range range = new Range(lowBound, hiBound);
        String generatedString = Str.generateRndString(range);
        
        assertTrue(generatedString.length() > 0);
    }
    
}
