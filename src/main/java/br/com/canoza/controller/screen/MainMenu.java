package br.com.canoza.controller.screen;

import java.util.Arrays;

/**
 * Initial screen of the game.
 */
public class MainMenu extends Screen {

  private static MainMenu mainMenu;
  private NewGame newGame;
  private LoadGame loadGame;

  protected MainMenu(NewGame newGame, LoadGame loadGame) {
    this.newGame = newGame;
    this.loadGame = loadGame;
    options = Arrays.asList("New Game", "Load Game");
    title = "Snake Way";
    message = "Hi I'm Goku! I'm sorry to say that you have died.\n"
        + "But, hey! Don't you worry, we can revive you with the Dragon Balls.\n"
        + "In the mean time, King Kai agreed to train you!\n"
        + "But you need to be in a proper level. So you'll need to follow the Snake Way!\n"
        + "If you walk the 25 fields will get to King Kai's. Be ware of the enemies on the way,\n"
        + "there'll be lot of them. If get to defeat enough enemies to gather 20 points of experience \n"
        + "I'll take you the rest of the way with instant transmission! Great reward right?\n"
        + "You'll notice that the Snake Way is not really a straight road, so if want, \n"
        + "you can jump 3 fields ahead, but that will make you more venerable to enemies,\n"
        + "it's your choice.\n"
        + "So let's do it!";

  }

  /**
   * Singleton implementation.
   *
   * @return an instance of {@link MainMenu}.
   */
  public static MainMenu getInstance() {
    if (mainMenu == null) {
      mainMenu = new MainMenu(NewGame.getInstance(), LoadGame.getInstance());
    }
    return mainMenu;
  }

  @Override
  public void render() {
    super.render();
    int opt = getOption();

    if (opt == 0) {
      newGame.render();
    } else {
      loadGame.render();
    }

  }

}
