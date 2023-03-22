package com.mahmoud.drone.utils;

public class SharedUtils {

    public static String generateCode(String entityShortCut, String shortKey, long id) {
        String seq = String.format("%08d", id).substring(0, 8);
        return entityShortCut + "-" + shortKey + "-" + seq;
    }

    public static String generateCode(String entityShortCut, long id) {
        String seq = String.format("%08d", id).substring(0, 8);
        return entityShortCut + "-" + seq;
    }

    private SharedUtils() {}
}
