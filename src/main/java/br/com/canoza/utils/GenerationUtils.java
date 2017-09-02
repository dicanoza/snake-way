package br.com.canoza.utils;

public class GenerationUtils {
    private GenerationUtils() {
    }

    public static int random(final int maxValue) {
        return (int) (Math.random() * maxValue);
    }
}
