package br.com.canoza.utils;

public class Preconditions {
  private Preconditions(){}

  public static void checkNotNull(final Object object, final String fieldName) {
    if (object == null) {
      throw new IllegalArgumentException(String.format("Parameter %s must not be null", fieldName));
    }
  }

  public static void checkNotBlank(final String object, final String fieldName) {
    if (object == null || object.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("Parameter %s must not be blank", fieldName));
    }
  }
}
