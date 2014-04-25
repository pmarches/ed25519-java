package net.i2p.crypto.eddsa.math;

import java.math.BigInteger;

public class Constants {
    public static final BigInteger ZERO = BigInteger.valueOf(0);
    public static final BigInteger ONE = BigInteger.valueOf(1);
    public static final BigInteger TWO = BigInteger.valueOf(2);
    public static final BigInteger THREE = BigInteger.valueOf(3);

    public static final int b = 256;
    public static final BigInteger q = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819949");
    public static final BigInteger qm2 = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819947");
    public static final BigInteger qp3 = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819952");
    public static final BigInteger qp5 = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819954");
    public static final BigInteger l = new BigInteger("7237005577332262213973186563042994240857116359379907606001950938285454250989");
    public static final FieldElement d = new FieldElement(new BigInteger("-4513249062541557337682894930092624173785641285191125241628941591882900924598840740"));
    public static final FieldElement d2 = d.multiply(FieldElement.TWO);
    public static final FieldElement I = new FieldElement(new BigInteger("19681161376707505956807079304988542015446066515923890162744021073123829784752"));
    public static final FieldElement By = new FieldElement(new BigInteger("46316835694926478169428394003475163141307993866256225615783033603165251855960"));
    public static final FieldElement Bx = new FieldElement(new BigInteger("15112221349535400772501151409588531511454012693041857206046113283949847762202"));
    public static final GroupElement B = GroupElement.p3(Bx, By, FieldElement.ONE, Bx.multiply(By));
    public static final BigInteger un = new BigInteger("57896044618658097711785492504343953926634992332820282019728792003956564819967");
}