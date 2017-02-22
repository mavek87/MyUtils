package com.matteoveroni.myutils;

import com.google.gson.Gson;
import org.junit.Test;

import static junit.framework.Assert.assertSame;

/**
 * @author Matteo Veroni
 */
public class JsonTest {

    @Test
    public void test_onlyTheSameGsonInstance_IsReturnedFrom_Json_getInstanceMethod() {
        Gson firstGsonObject = Json.getInstance();
        Gson secondGsonObject = Json.getInstance();

        assertSame("The two gson instances should be the same one", firstGsonObject, secondGsonObject);
    }
}
