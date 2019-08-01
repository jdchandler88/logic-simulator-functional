package com.darchan.logic.simulator.functional.intel8080;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RegisterFileTest {

    private static final boolean[] SMALL = new boolean[7];

    private static final boolean[] VALUE = new boolean[8];

    private static final boolean[] BIG = new boolean[9];

    @ParameterizedTest
    @MethodSource("getInputs")
    void storedValueIsExpected(boolean[] expected, boolean[] retrieved) {
        assertArrayEquals(expected, retrieved);
    }

    static Stream<Arguments> getInputs() {
        boolean[] f = createArrayWithNthBitEnabled(0);
        boolean[] a = createArrayWithNthBitEnabled(0);
        boolean[] b = createArrayWithNthBitEnabled(0);
        boolean[] c = createArrayWithNthBitEnabled(0);
        boolean[] d = createArrayWithNthBitEnabled(0);
        boolean[] e = createArrayWithNthBitEnabled(0);
        boolean[] h = createArrayWithNthBitEnabled(0);
        boolean[] l = createArrayWithNthBitEnabled(0);

        RegisterFile rf = new RegisterFile(f, a, b, c, d, e, h, l);
        return Stream.of(
                Arguments.of(f, rf.getF()),
                Arguments.of(a, rf.getA()),
                Arguments.of(b, rf.getB()),
                Arguments.of(c, rf.getC()),
                Arguments.of(d, rf.getD()),
                Arguments.of(e, rf.getE()),
                Arguments.of(h, rf.getH()),
                Arguments.of(l, rf.getL())
        );
    }

    static boolean[] createArrayWithNthBitEnabled(int n) {
        boolean[] array = new boolean[8];
        Arrays.fill(array, false);
        array[n] = true;
        return array;
    }

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void registerFileShouldThrowException(Class<? extends Throwable> clazz, boolean[] f, boolean[] a, boolean[] b, boolean[] c, boolean[] d, boolean[] e, boolean[] h, boolean[] l) {
        assertThrows(clazz, () -> new RegisterFile(f, a, b, c, d, e, h, l));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(IllegalArgumentException.class, VALUE, null, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, null, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, null, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, null, VALUE, VALUE, VALUE),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, VALUE, null, VALUE, VALUE),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, null, VALUE),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, null),


                Arguments.of(RangeValidationException.class, SMALL, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, BIG, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, SMALL, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, BIG, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, SMALL, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, BIG, VALUE, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, SMALL, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, BIG, VALUE, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, SMALL, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, BIG, VALUE, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, SMALL, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, BIG, VALUE, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, SMALL, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, BIG, VALUE),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, SMALL),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, BIG)
        );
    }



}