package com.matteoveroni.myutils;

import java.lang.reflect.Field;

/**
 * @author Matteo Veroni
 */
public class Singleton {

    /**
     * Method which reset a singleton attribute. Usefull for unit test singleton objectes.
     *
     * @param singletonClass The class of the singleton
     * @param singletonFieldName The attribute of the singleton to reset
     */
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
