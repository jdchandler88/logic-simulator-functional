package com.darchan.logic.simulator.functional.intel8080;

import com.darchan.logic.simulator.functional.Components;
import com.darchan.logic.simulator.functional.Range;
import com.darchan.logic.simulator.functional.Validations;

/**
 * Intel 8080 register file. Each register is 8 bits wide
 */
public class RegisterFile {

    /**
     * allowed width for registers in the file
     */
    private final Range REGISTER_WIDTH = new Range(8, 8);

    private final boolean[] f;

    private final boolean[] a;

    private final boolean[] b;

    private final boolean[] c;

    private final boolean[] d;

    private final boolean[] e;

    private final boolean[] h;

    private final boolean[] l;

    /**
     * Creates register file with the specified input data
     * @param f flags
     * @param a accumulator
     * @param b high word for BC register pair
     * @param c low word for BC register pair
     * @param d high word for DE register pair
     * @param e low word for DE register pair
     * @param h high word for HL register pair
     * @param l low word for HL register pair
     */
    public RegisterFile(boolean[] f, boolean[] a, boolean[] b, boolean[] c, boolean[] d, boolean[] e, boolean[] h, boolean[] l) {
        this.f = Components.validateNotNullAndWidth(f, REGISTER_WIDTH);
        this.a = Components.validateNotNullAndWidth(a, REGISTER_WIDTH);
        this.b = Components.validateNotNullAndWidth(b, REGISTER_WIDTH);
        this.c = Components.validateNotNullAndWidth(c, REGISTER_WIDTH);
        this.d = Components.validateNotNullAndWidth(d, REGISTER_WIDTH);
        this.e = Components.validateNotNullAndWidth(e, REGISTER_WIDTH);
        this.h = Components.validateNotNullAndWidth(h, REGISTER_WIDTH);
        this.l = Components.validateNotNullAndWidth(l, REGISTER_WIDTH);
    }

    /**
     * Gets A, accumulator
     * @return
     */
    public boolean[] getA() {
        return a;
    }

    /**
     * Gets B, high word for BC pair
     * @return
     */
    public boolean[] getB() {
        return b;
    }

    /**
     * Gets C, low word for BC pair
     * @return
     */
    public boolean[] getC() {
        return c;
    }

    /**
     * Gets D, high word for DE pair
     * @return
     */
    public boolean[] getD() {
        return d;
    }

    /**
     * Gets E, low word for DE pair
     * @return
     */
    public boolean[] getE() {
        return e;
    }

    /**
     * Gets F, flags.
     *
     * f[0] = carry
     * f[1] = ALWAYS 1
     * f[2] = parity
     * f[3] = 0
     * f[4] = auxiliary carry
     * f[5] = 0
     * f[6] = zero
     * f[7] = sign
     *
     * @return
     */
    public boolean[] getF() {
        return f;
    }

    /**
     * Gets H, high word for HL pair
     * @return
     */
    public boolean[] getH() {
        return h;
    }

    /**
     * Gets L, low word for HL pair
     * @return
     */
    public boolean[] getL() {
        return l;
    }
}
