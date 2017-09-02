package br.com.canoza.exception;

import java.io.IOException;

public class SnakeWayException extends RuntimeException {

  private static final long serialVersionUID = -4299115584175987198L;

  public SnakeWayException(String msg, IOException ex) {
    super(msg,ex);
  }
}
