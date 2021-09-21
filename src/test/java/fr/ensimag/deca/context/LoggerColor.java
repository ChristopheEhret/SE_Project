package fr.ensimag.deca.context;

import fr.ensimag.deca.tools.DecacInternalError;

public class LoggerColor {
    public static String colorBegin(int color) {
        return "\u001b[0;" + color + "m";
    }
    public static String colorEnd() {
        return "\u001b[m";
    }
    public static String colorText(String text, int color) {
        return colorBegin(color) + text + colorEnd();
    }
    public static String redBegin() {
        return "\u001b[0;31m";
    }
    public static String redEnd() {
        return "\u001b[m";
    }
    public static String redText(String text) {
        return redBegin() + text + redEnd();
    }
    public static String greenBegin() {
        return "\u001b[0;32m";
    }
    public static String greenEnd() {
        return "\u001b[m";
    }
    public static String greenText(String text) {
        return greenBegin() + text + greenEnd();
    }
    public static String yellowText(String text) {
        return "\u001b[0;33m" + text + colorEnd();
    }
    public static String orangeText(String text) {
        return "\u001b[0;91m" + text + colorEnd();
    }
}
