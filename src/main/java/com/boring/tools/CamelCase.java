package com.boring.tools;

import java.util.Arrays;

public class CamelCase {

    public static String big(String origin) {
        if (origin == null || origin.isEmpty()) throw new IllegalArgumentException("un-useful string");

        String spaceStr = origin.replaceAll("_", " ").replaceAll("-", " ").replaceAll("\\.", " ").replaceAll(",", " ").replaceAll("\t", " ").trim();

        if (spaceStr.isEmpty()) throw new IllegalArgumentException("un-useful string");

        StringBuilder sb = new StringBuilder();
        Arrays.stream(spaceStr.split(" ")).forEach(s -> sb.append(s.substring(0, 1).toUpperCase()).append(s.substring(1).toLowerCase()));

        return sb.toString();
    }

    public static String small(String origin) {
        return big(origin).substring(0, 1).toLowerCase() + big(origin).substring(1);
    }

    public static void main(String[] args) {
        System.out.println(big("cOm_BoRiNg.tOolS "));   // expect ComBoringTools
        System.out.println(small(" cOm,BoRiNg-tOolS")); // expect comBoringTools
    }
}
