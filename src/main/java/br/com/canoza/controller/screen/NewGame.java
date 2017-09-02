package br.com.canoza.controller.screen;

import static br.com.canoza.controller.engine.GameEngine.getOption;
import static br.com.canoza.controller.engine.GameEngine.printOptions;
import static br.com.canoza.controller.engine.GameEngine.printPlayerStatus;
import static br.com.canoza.controller.screen.Field.initField;
import static java.lang.System.out;

import br.com.canoza.controller.engine.GameEngine;
import br.com.canoza.domain.model.Player;
import br.com.canoza.service.PlayerService;
import java.util.Arrays;

public class NewGame extends Screen {

  private PlayerService playerService = new PlayerService();

  public NewGame() {
    title = "Snake Way - New Character";
    message = "This is time for you to start your adventure, let's choose a name for your "
        + "character";
  }

  @Override
  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    out.println("------------------------------------------------------------");
    out.println("Insert the Character Name:");
    String name = GameEngine.readString();

    Player player = playerService.createPlayer(name);
    out.println("This is your Character status");
    printPlayerStatus(player);
    out.println("Do you want to continue?");
    printOptions(Arrays.asList("Yes", "No"));
    if (getOption(1) == 1) {
      render();
    }
    initField(player, false).render();
  }


}
