package com.zsoltfabok.kata;

import static org.junit.Assert.assertEquals;

import java.util.Random;

public class FakeRandom extends Random {
    private static final long serialVersionUID = 1L;
    private int nextInt;
    private int nextIntArgument;
    public int nextInt(int boundary) {
        this.nextIntArgument = boundary;
        return nextInt;
    }
    public void setNextInt(int nextInt) {
        this.nextInt = nextInt;
    }
    public void assertNextIntArgument(int expectedNextIntArgument) {
        assertEquals(expectedNextIntArgument, nextIntArgument);
    }
}