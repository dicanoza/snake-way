package br.com.canoza.controller.screen;

import static br.com.canoza.controller.engine.GameEngine.printActionResultMessage;
import static br.com.canoza.controller.engine.GameEngine.printCharacterStatus;
import static br.com.canoza.controller.engine.GameEngine.printOptions;
import static java.lang.System.out;

import br.com.canoza.controller.engine.GameEngine;
import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import br.com.canoza.service.CharacterService;
import br.com.canoza.service.EncounterService;
import java.util.Arrays;
import java.util.Optional;

public class Field extends Screen {

  private CharacterService characterService = new CharacterService();
  private EncounterService encounterService = EncounterService.getInstance();

  private Character character;
  private Optional<Enemy> enemy;

  private Field() {

    title = "Field";
    message = "you get here";

  }


  private Field(Character character, Optional<Enemy> enemy) {
    this();
    this.character = character;
    this.enemy = enemy;
    if (enemy.isPresent()) {
      options = Arrays.asList("Fight", "Flee");
    } else {
      options = Arrays.asList("Run to the next field", "Jump to shortcut", "Save");
    }

  }

  public static Field initField(Character character, boolean hasEnemy) {
    if (hasEnemy) {
      return new Field(character, EncounterService.getInstance().getEncounter(character));
    }
    return new Field(character, Optional.empty());
  }

  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    out.println("------------------------------------------------------------");
    printCharacterStatus(character);
    if (enemy.isPresent()) {
      GameEngine.printEnemyStatus(enemy.get());
      printOptions(options);
      encounterRender();
    } else {
      printOptions(options);
      noEncounterRender();
    }

  }

  private void encounterRender() {
    int option = GameEngine.getOption(1);

    //TODO add some logit to increase the level when jumping
    if (option == 0) {
      fight();
    } else {
      //flee
      flee();

    }

  }

  private void flee() {
    if (encounterService.flee(character, enemy.get())) {
      printActionResultMessage("Fled!");
      initField(character, true).render();
    } else {
      printActionResultMessage("Unable to flee, fighting!!");
      fight();
    }
  }

  private void fight() {
    encounterService.fight(character, enemy.get());
    checkResult();
  }

  private void checkResult() {
    if (character.getHealth() <= 0) {
      out.println("GAME OVER");
    } else if (character.getHealth() > 0 && enemy.get().getHealth() > 0) {
      this.render();
    } else {
      out.println("You defeated the enemy!! Going to the next field!");
      initField(character, true).render();
    }
  }

  private void noEncounterRender() {
    int option = GameEngine.getOption(2);
    Field next = this;
    //TODO add some logit to increase the level when jumping
    switch (option) {
      case 0: {
        next = initField(character, true);
        break;
      }
      case 1: {
        next = initField(character, true);
        break;
      }
      case 2: {
        save();
      }
    }
    next.render();
  }

  private void save() {
    characterService.save(character);
    printActionResultMessage("Saved");
  }
}
