package com.matteoveroni.myutils;

import java.lang.reflect.Array;

/**
 * @author Matteo Veroni
 */
public class FormattedString {

    private String internalString;
    private Object[] args;

    public FormattedString() {
        this.internalString = "";
    }

    public FormattedString(String formattedString) {
        this.internalString = formattedString;
    }

    public FormattedString(String formattedString, Object... args) {
        this.internalString = formattedString;
        FormattedString f = new FormattedString();
        this.args = args;
    }

    public String getFormattedString() {
        return internalString;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setFormattedString(String formattedString) {
        this.internalString = formattedString;
    }

    public void setArgs(Object... newArgs) {
        if (args != null) {
            System.arraycopy(newArgs, 0, args, 0, args.length + newArgs.length);
        } else {
            args = newArgs;
        }
    }

    public FormattedString concat(FormattedString formattedStringToConcatenate) {
        String concatFormattedString = internalString + formattedStringToConcatenate.getFormattedString();

        Object[] concatArgs = args;
        Object[] args_formattedStringToConcatenate = formattedStringToConcatenate.getArgs();

        if (args != null && args_formattedStringToConcatenate != null) {
            concatArgs = concatenate(args, args_formattedStringToConcatenate);
        } else if (args == null && args_formattedStringToConcatenate != null) {
            concatArgs = args_formattedStringToConcatenate;
        }
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
