package com.matteoveroni.myutils;

/**
 * @author Matteo Veroni
 */
public class IntRange {

    private final int lowBorder;
    private final int highBorder;

    public IntRange(int lowRangeBorder, int highRangeBorder) {
        if (lowRangeBorder > highRangeBorder) {
            throw new IllegalArgumentException("Low range border is greater than high range border");
        }

        this.lowBorder = lowRangeBorder;
        this.highBorder = highRangeBorder;
    }

    public int getLowBorder() {
        return lowBorder;
    }

    public int getHighBorder() {
        return highBorder;
    }

    public int getDimension() {
        return highBorder - lowBorder;
    }
}
