package br.com.canoza.controller.screen;

import br.com.canoza.domain.model.Enemy;
import br.com.canoza.domain.model.Player;
import br.com.canoza.service.EncounterService;
import br.com.canoza.service.PlayerService;
import java.util.Arrays;
import java.util.Optional;

public class Field extends Screen {

  private PlayerService playerService = new PlayerService();

  private Optional<Enemy> enemy;

  private Field() {

    title = "Field";
    message = "you get here";

  }

  private Field(Optional<Enemy> enemy) {
    this();
    this.enemy = enemy;
    if (enemy.isPresent()) {
      options = Arrays.asList("Fight", "Flee");
    } else {
      options = Arrays.asList("Run to the next field", "Jump to shortcut");
    }

  }

  public static Field initField(Player player, boolean hasEnemy) {
    if (hasEnemy) {
      return new Field(EncounterService.getInstance().getEncounter(player));
    }
    return new Field(Optional.empty());
  }


}
