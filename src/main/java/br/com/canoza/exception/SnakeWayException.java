package br.com.canoza.exception;

public class SnakeWayException extends RuntimeException {

  private static final long serialVersionUID = -4299115584175987198L;

  public SnakeWayException(String msg, Exception ex) {
    super(msg, ex);
  }
}
