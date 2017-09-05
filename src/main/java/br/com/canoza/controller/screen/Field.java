package br.com.canoza.controller.screen;


import static java.lang.System.out;

import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import br.com.canoza.service.CharacterService;
import br.com.canoza.service.EncounterService;
import java.util.Arrays;
import java.util.Optional;

public class Field extends Screen {

  private static final int END = 25;
  private static Field field;

  private EncounterService encounterService;
  private CharacterService characterService;

  private Character character;
  private Enemy enemy;

  protected Field(EncounterService encounterService, CharacterService characterService) {
    this.encounterService = encounterService;
    this.characterService = characterService;
  }

  public static Field getInstance() {
    if (field == null) {
      field = new Field(EncounterService.getInstance(), CharacterService.getInstance());
    }
    return field;
  }

  public void initSafeField(Character character) {
    initField(character, false, false);
  }

  private void initField(Character character, boolean canHaveEnemy, boolean jump) {
    if (canHaveEnemy) {
      configure(character, encounterService.getEncounter(character, jump));
      checkMapPosition(character.getMapPosition());
    } else {
      configure(character, Optional.empty());
      checkMapPosition(character.getMapPosition());
    }

  }

  private void configure(Character character, Optional<Enemy> enemy) {
    title = "Field";
    message = "you get here";
    this.character = character;

    if (enemy.isPresent()) {
      this.enemy = enemy.get();
      options = Arrays.asList("Fight", "Flee", "Exit");
    } else {
      this.enemy = null;
      options = Arrays.asList("Run to the next field", "Jump to shortcut", "Save & exit", "Exit");
    }
  }

  private void checkMapPosition(int mapPosition) {
    if (mapPosition >= END) {
      out.println("Congratulations!! You have reached King Kai planet!");
    } else {
      this.render();
    }

  }

  protected void runToFieldWithEnemy(Character character) {
    initField(character, true, false);
  }

  protected void jumpToFieldWithEnemy(Character character) {
    initField(character, true, true);
  }

  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    out.println("------------------------------------------------------------");
    out.println(character);
    if (enemy != null) {
      out.println("------------------------------------------------------------");
      out.println("Enemy appeared");
      out.println("------------------------------------------------------------");
      out.println(enemy);
      out.println("------------------------------------------------------------");
      printOptions(options);
      encounterRender();
    } else {
      printOptions(options);
      noEncounterRender();
    }

  }

  private void encounterRender() {
    int option = getOption();
    switch (option) {
      case 0: {
        fight();
        break;
      }
      case 1: {
        flee();
        break;
      }
      case 2: {
        return;
      }
    }
  }

  private void flee() {
    if (encounterService.flee(character, enemy)) {
      printActionResultMessage("Fled!");
      initSafeField(character);
    } else {
      printActionResultMessage("Unable to flee, fighting!!");
      fight();
    }
  }

  private void fight() {
    checkFightResult(encounterService.fight(character, enemy));
  }

  private void checkFightResult(int strike) {
    if (strike > 0) {
      hitEnemy(strike);
    } else {
      hitReceived(strike);
    }
  }

  private void hitReceived(int strike) {
    printActionResultMessage(String.format("Enemy hits you %d points.", Math.abs(strike)));
    if (character.getHealth() <= 0) {
      out.println("Your life has ended.");
      out.println("GAME OVER");
    } else {
      this.render();
    }
  }

  private void hitEnemy(int strike) {
    printActionResultMessage(String.format("You hit the enemy %d points.", strike));
    if (enemy.getHealth() > 0) {
      this.render();
    } else {
      enemyDefeated();
    }
  }

  private void enemyDefeated() {
    printActionResultMessage(String
        .format("You defeated the enemy, and gained %d points of experience.",
            enemy.getGivenExperience()));
    character.addExperience(enemy.getGivenExperience());

    if (character.getExperience() >= 20) {
      printActionResultMessage(
          "Congratulations!! You have reached a proper level, I'll take you to King Kai!");
    } else {
      out.println("Going to next field");
      character.resetHealth();
      runToFieldWithEnemy(character);
    }
  }

  private void noEncounterRender() {
    int option = getOption();
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
      case 3: {
        return;
      }
    }
  }

  private void save() {
    characterService.save(character);
    printActionResultMessage("Saved");
  }
}
