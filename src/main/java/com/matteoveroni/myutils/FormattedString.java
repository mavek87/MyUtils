package com.matteoveroni.myutils;

import java.lang.reflect.Array;

/**
 * @author Matteo Veroni
 */
public class FormattedString {

    private final String formattedString;
    private Object[] args;

    public FormattedString(String formattedString) {
        this.formattedString = formattedString;
    }

    public FormattedString(String formattedString, Object... args) {
        this.formattedString = formattedString;
        this.args = args;
    }

    public String getFormattedString() {
        return formattedString;
    }

    public Object[] getArgs() {
        return args;
    }

    public FormattedString concat(FormattedString formattedStringToConcatenate) {
        String concatFormattedString = formattedString + formattedStringToConcatenate.getFormattedString();
        Object[] concatArgs = concatenate(args, formattedStringToConcatenate.getArgs());
        return new FormattedString(concatFormattedString, concatArgs);
    }

    private <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }
}
