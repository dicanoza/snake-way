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
    message = "Hi I'm Goku";

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
