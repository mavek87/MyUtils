package com.matteoveroni.myutils;

import java.lang.reflect.Field;

/**
 *
 * @author veroni
 */
public class Singleton {

    public static void resetAttribute(Class singletonClass, String singletonFieldName) {
        Field instance;
        try {
            instance = singletonClass.getDeclaredField(singletonFieldName);
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
