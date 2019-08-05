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

    /**
     * allowed width for program counter and stack pointer
     */
    private final Range PC_SP_WIDTH = new Range(16, 16);

    private final Boolean[] f;

    private final Boolean[] a;

    private final Boolean[] b;

    private final Boolean[] c;

    private final Boolean[] d;

    private final Boolean[] e;

    private final Boolean[] h;

    private final Boolean[] l;

    private final Boolean[] pc;

    private final Boolean[] sp;

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
     * @param pc program counter
     * @param sp stack pointer
     */
    public RegisterFile(Boolean[] f, Boolean[] a, Boolean[] b, Boolean[] c, Boolean[] d, Boolean[] e, Boolean[] h, Boolean[] l, Boolean[] pc, Boolean[] sp) {
        this.f = Components.validateNotNullAndWidth(f, REGISTER_WIDTH);
        this.a = Components.validateNotNullAndWidth(a, REGISTER_WIDTH);
        this.b = Components.validateNotNullAndWidth(b, REGISTER_WIDTH);
        this.c = Components.validateNotNullAndWidth(c, REGISTER_WIDTH);
        this.d = Components.validateNotNullAndWidth(d, REGISTER_WIDTH);
        this.e = Components.validateNotNullAndWidth(e, REGISTER_WIDTH);
        this.h = Components.validateNotNullAndWidth(h, REGISTER_WIDTH);
        this.l = Components.validateNotNullAndWidth(l, REGISTER_WIDTH);
        this.pc = Components.validateNotNullAndWidth(pc, PC_SP_WIDTH);
        this.sp = Components.validateNotNullAndWidth(sp, PC_SP_WIDTH);
    }

    /**
     * Gets A, accumulator
     * @return
     */
    public Boolean[] getA() {
        return a;
    }

    /**
     * Gets B, high word for BC pair
     * @return
     */
    public Boolean[] getB() {
        return b;
    }

    /**
     * Gets C, low word for BC pair
     * @return
     */
    public Boolean[] getC() {
        return c;
    }

    /**
     * Gets D, high word for DE pair
     * @return
     */
    public Boolean[] getD() {
        return d;
    }

    /**
     * Gets E, low word for DE pair
     * @return
     */
    public Boolean[] getE() {
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
    public Boolean[] getF() {
        return f;
    }

    /**
     * Gets H, high word for HL pair
     * @return
     */
    public Boolean[] getH() {
        return h;
    }

    /**
     * Gets L, low word for HL pair
     * @return
     */
    public Boolean[] getL() {
        return l;
    }

    /**
     * Gets program counter
     * @return
     */
    public Boolean[] getPc() {
        return pc;
    }

    /**
     * Gets stack pointer
     * @return
     */
    public Boolean[] getSp() {
        return sp;
    }
}
