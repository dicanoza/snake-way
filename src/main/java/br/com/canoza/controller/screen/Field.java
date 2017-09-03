package br.com.canoza.controller.screen;

import static br.com.canoza.controller.engine.GameEngine.printActionResultMessage;
import static br.com.canoza.controller.engine.GameEngine.printOptions;
import static java.lang.System.*;
import static java.lang.System.out;

import br.com.canoza.controller.engine.GameEngine;
import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import br.com.canoza.service.CharacterService;
import br.com.canoza.service.EncounterService;
import java.util.Arrays;
import java.util.Optional;

public class Field extends Screen {

  private static final EncounterService encounterService = EncounterService.getInstance();
  private static final int END = 25;
  private CharacterService characterService = new CharacterService();

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
      options = Arrays.asList("Run to the next field", "Jump to shortcut", "Save & exit");
    }

  }

  public static void initSafeField(Character character) {
    initField(character, false, false);
  }

  private static void initField(Character character, boolean hasEnemy, boolean jump) {
    if (hasEnemy) {
      checkMapPosition(character.getMapPosition(),new Field(character, encounterService.getEncounter(character, jump)));
    }
    checkMapPosition(character.getMapPosition(), new Field(character, Optional.empty()));
  }

  private void runToFieldWithEnemy(Character character) {
    initField(character, true, false);
  }

  private void jumpToFieldWithEnemy(Character character) {
    initField(character, true, true);
  }

  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    out.println("------------------------------------------------------------");
    out.println(character);
    if (enemy.isPresent()) {
      out.println("------------------------------------------------------------");
      out.println("Enemy appeared");
      out.println("------------------------------------------------------------");
      out.println(enemy.get());
      out.println("------------------------------------------------------------");
      printOptions(options);
      encounterRender();
    } else {
      printOptions(options);
      noEncounterRender();
    }

  }

  private void encounterRender() {
    int option = GameEngine.getOption(1);
    if (option == 0) {
      fight();
    } else {
      flee();
    }

  }

  private void flee() {
    if (encounterService.flee(character, enemy.get())) {
      printActionResultMessage("Fled!");
      initSafeField(character);
    } else {
      printActionResultMessage("Unable to flee, fighting!!");
      fight();
    }
  }

  private void fight() {
    checkFightResult(encounterService.fight(character, enemy.get()));
  }

  private void checkFightResult(int strike) {
    if (strike > 0) {
      printActionResultMessage(String.format("You hit the enemy %d points.", strike));
      if (enemy.get().getHealth() > 0) {
        this.render();
      } else {
        printActionResultMessage(String
            .format("You defeated the enemy, and gained %d points of experience.",
                enemy.get().getGivenExperience()));
        character.addExperience(enemy.get().getGivenExperience());

        if (character.getExperience() >= 20) {
          printActionResultMessage(
              "Congratulations!! You have reached a proper level, I'll take you to King Kai!");
        } else {
          out.println("Going to next field");
          character.resetHealth();
          runToFieldWithEnemy(character);
        }
      }
    } else {
      printActionResultMessage(String.format("Enemy hits you %d points.", Math.abs(strike)));
      if (character.getHealth() <= 0) {
        out.println("Your life has ended.");
        out.println("GAME OVER");
      } else {
        this.render();
      }
    }
  }

  private static void checkMapPosition(int mapPosition, Field field){
    if(mapPosition >= END){
      out.println("Congratulations!! You have reached King Kai planet!");
    }else{
      field.render();
    }

  }

  private void noEncounterRender() {
    int option = GameEngine.getOption(2);
    switch (option) {
      case 0: {
        character.move(1);
        runToFieldWithEnemy(character);
        break;
      }
      case 1: {
        character.move(3);
        jumpToFieldWithEnemy(character);
        break;
      }
      case 2: {
        save();
        return;
      }
    }
  }

  private void save() {
    characterService.save(character);
    printActionResultMessage("Saved");
  }
}
