package com.cikezxy.sandbox.guava;

import static com.google.common.base.Preconditions.*;

import org.junit.Test;

public class PreconditionTest {

    @Test(expected = NullPointerException.class)
    public void testNotNull() {
        checkNotNull(null, "input can not be null");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckArgument() {
        int i = 5;
        int j = 6;
        checkArgument(i > j, "Illegal argument:i=%s, j=%s", i, j);
    }

    @Test(expected = IllegalStateException.class)
    public void testCheckState() {
        int i = 5;
        int j = 6;
        checkState(i > j, "Illegal argument:i=%s, j=%s", i, j);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckElementIndex() {
        checkElementIndex(3, 3, "Index is out of range");
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void testCheckPositionIndex() {
        checkPositionIndex(3, 4, "Index is out of range");
    }
}
