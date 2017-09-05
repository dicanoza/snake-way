package br.com.canoza.controller;

import br.com.canoza.controller.screen.MainMenu;

public class App {

  public static void main(String[] args) {
    MainMenu.getInstance().render();
  }
}
