package br.com.canoza.controller.screen;

import br.com.canoza.domain.model.Enemy;
import br.com.canoza.domain.model.Character;
import br.com.canoza.service.EncounterService;
import br.com.canoza.service.CharacterService;
import java.util.Arrays;
import java.util.Optional;

public class Field extends Screen {

  private CharacterService characterService = new CharacterService();

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

  public static Field initField(Character character, boolean hasEnemy) {
    if (hasEnemy) {
      return new Field(EncounterService.getInstance().getEncounter(character));
    }
    return new Field(Optional.empty());
  }


}
