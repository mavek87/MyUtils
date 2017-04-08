package com.matteoveroni.myutils;

/**
 * @author Matteo Veroni
 */
public class Range {

    private final int lowBorder;
    private final int highBorder;

    public Range(int lowRangeBorder, int highRangeBorder) {
        this.lowBorder = lowRangeBorder;
        this.highBorder = highRangeBorder;
    }

    public int getLowBorder() {
        return lowBorder;
    }

    public int getHighBorder() {
        return highBorder;
    }
}
