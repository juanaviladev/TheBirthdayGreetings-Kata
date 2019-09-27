package com.greetings.legacy.utils;

public class Log {

    public static void d(String msg) {
        System.out.println("DEBUG: "+msg);
    }

    public static void e(String msg) {
        System.err.println("ERROR: "+msg);
    }

}
