package br.com.canoza.controller.screen;

import static br.com.canoza.controller.engine.GameEngine.getOption;

import java.util.Arrays;

public class MainMenu extends Screen {

  public MainMenu() {
    options = Arrays.asList("New Game", "Load Game");
    title = "Snake Way";
    message = "Hi I'm Goku";

  }

  @Override
  public void render() {
    super.render();
    int opt = getOption(options.size());

    if (opt == 0) {
      new NewGame().render();
    } else {
      new LoadGame().render();
    }

  }

}
