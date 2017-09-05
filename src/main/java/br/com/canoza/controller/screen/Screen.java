package br.com.canoza.controller.screen;

import static java.lang.String.format;
import static java.lang.System.err;
import static java.lang.System.out;

import java.util.List;
import java.util.Scanner;

/**
 * Basic class for all the screens, defines some utils methods and basic behavior.
 */
public abstract class Screen {

  public static final String LINE_SEPARATOR =
      "------------------------------------------------------------";
  public static final String MENU_SEPARATOR =
      ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
  protected String title;
  protected String message;
  protected List<String> options;

  /**
   * This method is responsible for rendering the screen, subclasses of Screen can override this
   * method to alter the basic behavior. The basic behavior is to print the Title, Message and
   * options for input.
   */
  public void render() {
    out.println(MENU_SEPARATOR);
    out.println(title);
    out.println(MENU_SEPARATOR);
    out.println(message);
    out.println(LINE_SEPARATOR);

    printOptions(options);

  }

  private Scanner getScanner() {
    return new Scanner(System.in);
  }

  /**
   * Reads the input and checks if the value is an integer and it is within the range of the
   * options.
   *
   * @return the integer read.
   */
  protected int getOption() {
    return getOption(options.size() - 1);
  }

  /**
   * Reads the input and checks if the input is within the range of 0 > input <= max.
   *
   * @param max accepted for the input.
   * @return the input integer read.
   */
  protected int getOption(int max) {
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

  /**
   * Reads an {@link String} from the input.
   *
   * @return the String read.
   */
  String readString() {
    return getScanner().next();
  }

  /**
   * Prints the options to the standard output.
   *
   * @param options to be printed.
   */
  public void printOptions(List<String> options) {
    out.println(LINE_SEPARATOR);
    out.println("Options:");
    for (int i = 0; i < options.size(); i++) {
      out.println(format("(%d) - %s", i, options.get(i)));
    }
  }

  /**
   * Prints the message with a line before. It's the basic way to print the result of actions.
   *
   * @param message to be printed.
   */
  public void printActionResultMessage(String message) {
    out.println(LINE_SEPARATOR);
    out.println(message);
  }

}
