package com.darchan.logic.simulator.functional.intel8080;

import com.darchan.logic.simulator.functional.exception.RangeValidationException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RegisterFileTest {

    private static final Boolean[] SMALL = new Boolean[7];

    private static final Boolean[] VALUE = new Boolean[8];

    private static final Boolean[] VALUE16 = new Boolean[16];

    private static final Boolean[] BIG = new Boolean[17];


    @ParameterizedTest
    @MethodSource("getInputs")
    void storedValueIsExpected(Boolean[] expected, Boolean[] retrieved) {
        assertArrayEquals(expected, retrieved);
    }

    static Stream<Arguments> getInputs() {
        Boolean[] f = createArrayWithNthBitEnabled(0);
        Boolean[] a = createArrayWithNthBitEnabled(0);
        Boolean[] b = createArrayWithNthBitEnabled(0);
        Boolean[] c = createArrayWithNthBitEnabled(0);
        Boolean[] d = createArrayWithNthBitEnabled(0);
        Boolean[] e = createArrayWithNthBitEnabled(0);
        Boolean[] h = createArrayWithNthBitEnabled(0);
        Boolean[] l = createArrayWithNthBitEnabled(0);

        Boolean[] pc = new Boolean[16];
        Arrays.fill(pc, false);
        pc[10] = true;

        Boolean[] sp = new Boolean[16];
        Arrays.fill(sp, false);
        sp[11] = true;

        RegisterFile rf = new RegisterFile(f, a, b, c, d, e, h, l, pc, sp);
        return Stream.of(
                Arguments.of(f, rf.getF()),
                Arguments.of(a, rf.getA()),
                Arguments.of(b, rf.getB()),
                Arguments.of(c, rf.getC()),
                Arguments.of(d, rf.getD()),
                Arguments.of(e, rf.getE()),
                Arguments.of(h, rf.getH()),
                Arguments.of(l, rf.getL()),
                Arguments.of(pc, rf.getPc()),
                Arguments.of(sp, rf.getSp())
        );
    }

    static Boolean[] createArrayWithNthBitEnabled(int n) {
        Boolean[] array = new Boolean[8];
        Arrays.fill(array, false);
        array[n] = true;
        return array;
    }

    @ParameterizedTest
    @MethodSource("getInputsForExceptions")
    void registerFileShouldThrowException(Class<? extends Throwable> clazz, Boolean[] f, Boolean[] a, Boolean[] b, Boolean[] c, Boolean[] d, Boolean[] e, Boolean[] h, Boolean[] l, Boolean[] pc, Boolean[] sp) {
        assertThrows(clazz, () -> new RegisterFile(f, a, b, c, d, e, h, l, pc, sp));
    }

    static Stream<Arguments> getInputsForExceptions() {
        return Stream.of(
                Arguments.of(IllegalArgumentException.class, null, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, null, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, null, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, null, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, null, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, VALUE, null, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, null, VALUE, VALUE16, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, null, VALUE16, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, null, VALUE16),
                Arguments.of(IllegalArgumentException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, null),


                Arguments.of(RangeValidationException.class, SMALL, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, BIG, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, SMALL, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, BIG, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, SMALL, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, BIG, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, SMALL, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, BIG, VALUE, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, SMALL, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, BIG, VALUE, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, SMALL, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, BIG, VALUE, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, SMALL, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, BIG, VALUE, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, SMALL, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, BIG, VALUE16, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, SMALL, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, BIG, VALUE16),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, SMALL),
                Arguments.of(RangeValidationException.class, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE, VALUE16, BIG)
        );
    }



}