package br.com.canoza.utils;

public class GenerationUtils {

  private GenerationUtils() {
  }

  public static int random(final long maxValue) {
    return (int) (Math.random() * maxValue);
  }
}
