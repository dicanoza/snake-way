package br.com.canoza.controller.engine;

import static java.lang.String.format;
import static java.lang.System.err;
import static java.lang.System.out;

import br.com.canoza.controller.screen.MainMenu;
import br.com.canoza.domain.model.Player;
import java.util.List;
import java.util.Scanner;

public class GameEngine {

  private static final Scanner scanner = new Scanner(System.in);

  private GameEngine() {
  }

  public static void init() {
    new MainMenu().render();
  }

  public static int getOption(int max) {
    String input = scanner.next();
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

  public static String readString() {
    return scanner.next();
  }

  public static void printPlayerStatus(Player player) {
    out.println("Player Status");
    out.println(format("Name: %s", player.getName()));
    out.println(format("Experience: %s", player.getExperience()));
    out.println(format("MaxHealth: %s", player.getMaxHealth()));
    out.println(format("Health: %s", player.getMaxHealth()));
    out.println(format("Strength: %s", player.getStrength()));
    out.println(format("Speed: %s", player.getSpeed()));
  }

  public static void printOptions(List<String> options) {
    out.println("Options:");
    for (int i = 0; i < options.size(); i++) {
      out.println(format("(%d) - %s", i, options.get(i)));
    }
  }
}
