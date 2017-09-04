package br.com.canoza.controller.screen;

import static br.com.canoza.controller.engine.GameEngine.getOption;

import java.util.Arrays;

public class MainMenu extends Screen {

  private static MainMenu mainMenu;
  private NewGame newGame;
  private LoadGame loadGame;

  private MainMenu(NewGame newGame, LoadGame loadGame) {
    this.newGame = newGame;
    options = Arrays.asList("New Game", "Load Game");
    title = "Snake Way";
    message = "Hi I'm Goku";

  }

  public static MainMenu getInstance() {
    if (mainMenu == null) {
      mainMenu = new MainMenu(NewGame.getInstance(),LoadGame.getInstance());
    }
    return mainMenu;
  }

  @Override
  public void render() {
    super.render();
    int opt = getOption(options.size());

    if (opt == 0) {
      newGame.render();
    } else {
      loadGame.render();
    }

  }

}
