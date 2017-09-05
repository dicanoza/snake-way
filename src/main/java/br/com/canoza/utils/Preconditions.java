package br.com.canoza.utils;

public class Preconditions {

  private Preconditions() {
  }

  /**
   * Check if the object parameter is not null, if it is null an {@link IllegalArgumentException}
   * will be raised.
   *
   * @param object to be checked.
   * @param fieldName used as argument of the exception, if the object is null.
   */
  public static void checkNotNull(final Object object, final String fieldName) {
    if (object == null) {
      throw new IllegalArgumentException(String.format("Parameter %s must not be null", fieldName));
    }
  }

  /**
   * Check if the object parameter is not blank, if it is null an {@link IllegalArgumentException}
   * will be raised.
   *
   * @param object to be checked.
   * @param fieldName used as argument of the exception, if the object is blank.
   */
  public static void checkNotBlank(final String object, final String fieldName) {
    if (object == null || object.isEmpty()) {
      throw new IllegalArgumentException(
          String.format("Parameter %s must not be blank", fieldName));
    }
  }
}
