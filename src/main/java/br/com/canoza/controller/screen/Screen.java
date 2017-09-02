package br.com.canoza.controller.screen;

import static br.com.canoza.controller.engine.GameEngine.printOptions;
import static java.lang.System.out;

import java.util.List;
import java.util.Map;

public abstract class Screen{

  protected String title;
  protected String message;
  protected List<String> options;
  protected Map<Integer, Screen> nextScreen;

  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    out.println("------------------------------------------------------------");
    printOptions(options);

  }
}
