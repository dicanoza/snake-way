package br.com.canoza.controller.screen;

import static br.com.canoza.controller.engine.GameEngine.getOption;

import java.util.Arrays;
import java.util.HashMap;

public class MainMenu extends Screen {

  public MainMenu() {
    options = Arrays.asList("New Game", "Load");
    title = "Snake Way";
    message = "Hi I'm Goku";
    nextScreen = new HashMap<>();
    nextScreen.put(0,new NewGame());

  }

  @Override
  public void render() {
    super.render();
    int opt = getOption(nextScreen.keySet().size());
    nextScreen.get(opt).render();
  }

}
