package br.com.canoza.controller.engine;

import static java.lang.String.format;
import static java.lang.System.err;
import static java.lang.System.out;

import br.com.canoza.controller.screen.MainMenu;
import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
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

  public static void printCharacterStatus(Character character) {
    out.println("Character Status");
    out.println("------------------------------------------------------------");
    out.println(format("Name: %s", character.getName()));
    out.println("------------------------------------------------------------");
    out.println(format("Experience: %s", character.getExperience()));
    out.println(format("MaxHealth: %s", character.getMaxHealth()));
    out.println(format("Health: %s", character.getHealth()));
    out.println(format("Strength: %s", character.getStrength()));
    out.println(format("Speed: %s", character.getSpeed()));
  }

  public static void printEnemyStatus(Enemy enemy) {
    out.println("------------------------------------------------------------");
    out.println("Enemy Status");
    out.println("------------------------------------------------------------");
    out.println(format("Given Experience: %s", enemy.getGivenExperience()));
    out.println(format("Health: %s", enemy.getHealth()));
    out.println(format("Strength: %s", enemy.getStrength()));
    out.println(format("Speed: %s", enemy.getSpeed()));
  }

  public static void printOptions(List<String> options) {
    out.println("------------------------------------------------------------");
    out.println("Options:");
    for (int i = 0; i < options.size(); i++) {
      out.println(format("(%d) - %s", i, options.get(i)));
    }
  }
  public static void printActionResultMessage(String message){
    out.println("------------------------------------------------------------");
    out.println(message);
  }
}

