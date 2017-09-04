package br.com.canoza.controller.screen;

import static java.lang.String.format;
import static java.lang.System.err;
import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

public abstract class Screen {

  protected String title;
  protected String message;
  protected List<String> options;

  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    out.println("------------------------------------------------------------");

    printOptions(options);

  }

  public Scanner getScanner() {
    return new Scanner(System.in);
  }

  public int getOption() {
    return getOption(options.size() - 1);
  }

  public int getOption(int max) {
    String input = readString();
    try {
      int opt = Integer.parseInt(input);
      if (opt < 0 || opt > max) {
        err.println("Invalid options, please insert a valid number");
        return getOption(max);
      }
      return opt;
    } catch (NumberFormatException e) {
      err.println("Invalid options, please insert a valid number");
      return getOption(max);
    }
  }

  public String readString() {
    return getScanner().next();
  }


  public void printOptions(List<String> options) {
    out.println("------------------------------------------------------------");
    out.println("Options:");
    for (int i = 0; i < options.size(); i++) {
      out.println(format("(%d) - %s", i, options.get(i)));
    }
  }

  public void printActionResultMessage(String message) {
    out.println("------------------------------------------------------------");
    out.println(message);
  }

}
