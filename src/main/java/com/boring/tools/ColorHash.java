package com.boring.tools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fusesource.jansi.Ansi;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author hiram 2020年12月25日 17:45
 */
public class ColorHash {
    private static final Logger logger = LogManager.getLogger(ColorHash.class);
    private static MessageDigest digester;

    static {
        try {
            digester = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String genColor(String s) {
        String encrypt = encrypt(s);
        return encrypt == null ? null : "#" + encrypt.substring(encrypt.length() - 6);
    }

    public static String encrypt(String str) {
        if (digester == null || str == null || str.length() == 0) {
            return null;
        }

        synchronized (digester) {
            try {
                digester.update(str.getBytes("UTF-8"));
                String s1 = new BigInteger(1, digester.digest()).toString(16);
                //补齐BigInteger省略的前置0
                return new String(new char[32 - s1.length()]).replace("\0", "0") + s1;
            } catch (Exception e) {
                //一般不会有异常抛出， 该死的Java受检异常，导致丑陋的代码
            }
        }

        return null;
    }

    public static void printColor(String s) {
        String str = genColor(s);
        assert str != null;
        String str2 = str.substring(1, 3);
        String str3 = str.substring(3, 5);
        String str4 = str.substring(5, 7);
        int red = Integer.parseInt(str2, 16);
        int green = Integer.parseInt(str3, 16);
        int blue = Integer.parseInt(str4, 16);
        logger.info(Ansi.ansi().fgRgb(red, green, blue).a(s).reset());
    }

    public static void main(String[] args) {
        printColor("com");
        printColor("boring");
        printColor("tools");
    }
}
