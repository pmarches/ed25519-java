package net.i2p.crypto.eddsa;

import java.math.BigInteger;

/**
 * @author str4d
 *
 */
public class Utils {
    /**
     * Constant-time byte comparison.
     */
    public static int equal(int b, int c) {
        int result = 0;
        for (int i = 0; i < 8; i++) {
            result |= (b >> i & 1) ^ (c >> i & 1);
        }
        return result == 0 ? 1 : 0;
    }

    /**
     * Constant-time determine if byte is negative.
     * @param b the byte to check.
     * @return 1 if the byte is negative, 0 otherwise.
     */
    public static int negative(int b) {
        return (b >> 8) & 1;
    }

    /**
     * I don't really know what this method does.
     * @param a
     * @return
     */
    public static byte[] slide(byte[] a) {
        byte[] r = new byte[256];
        int i;
        int b;
        int k;

        for (i = 0;i < 256;++i) {
            r[i] = (byte) (1 & (a[i >> 3] >> (i & 7)));
        }

        for (i = 0;i < 256;++i) {
            if (r[i] != 0) {
                for (b = 1; b <= 6 && i + b < 256; ++b) {
                    if (r[i + b] != 0) {
                        if (r[i] + (r[i + b] << b) <= 15) {
                            r[i] += r[i + b] << b; r[i + b] = 0;
                        } else if (r[i] - (r[i + b] << b) >= -15) {
                            r[i] -= r[i + b] << b;
                            for (k = i + b; k < 256; ++k) {
                                if (r[k] == 0) {
                                    r[k] = 1;
                                    break;
                                }
                                r[k] = 0;
                            }
                        } else
                            break;
                    }
                }
            }
        }

        return r;
    }

    /**
     * Get the i'th bit of a byte array.
     * @param h the byte array.
     * @param i the bit index.
     * @return the value of the i'th bit in h.
     */
    public static int bit(byte[] h, int i) {
        return h[i/8] >> (i%8) & 1;
    }

    /**
     * From the Ed25519 paper:
     * Here we interpret 2b-bit strings in little-endian form as integers in
     * {0, 1,..., 2^(2b)-1}.
     * @param h the output of a hash function.
     * @return 2^h
     */
    public static BigInteger Hint(byte[] h) {
        // Reverse h
        for (int i = 0; i < h.length/2; i++) {
            byte tmp = h[i];
            h[i] = h[h.length-1-i];
            h[h.length-1-i] = tmp;
        }
        return new BigInteger(1, h);
    }

    /**
     * Converts bytes to a hex string.
     * @param raw the byte[] to be converted.
     * @return the hex representation as a string.
     */
    public static String getHex(byte[] raw) {
        if ( raw == null ) {
            return null;
        }
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(Character.forDigit((b & 0xF0) >> 4, 16))
            .append(Character.forDigit((b & 0x0F), 16));
        }
        return hex.toString();
    }

    /**
     * Converts a hex string to bytes.
     * @param s the hex string to be converted.
     * @return the byte[]
     */
    public static byte[] hexToBytes(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
}
