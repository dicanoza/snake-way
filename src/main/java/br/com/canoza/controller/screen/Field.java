package br.com.canoza.controller.screen;

import static java.lang.System.out;

import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import br.com.canoza.service.CharacterService;
import br.com.canoza.service.EncounterService;
import java.util.Arrays;
import java.util.Optional;

/**
 * Screen to manage the game itself, responsible the fights, exploration and defining the end of the
 * game.
 */
public class Field extends Screen {

  private static final int END_OF_THE_WAY = 25;
  private static final int MAX_EXPERIENCE = 20;
  private static Field field;

  private EncounterService encounterService;
  private CharacterService characterService;

  private Character character;
  private Enemy enemy;

  protected Field(EncounterService encounterService, CharacterService characterService) {
    this.encounterService = encounterService;
    this.characterService = characterService;
  }

  /**
   * Singleton implementation.
   *
   * @return an instance of {@link Field}.
   */
  public static Field getInstance() {
    if (field == null) {
      field = new Field(EncounterService.getInstance(), CharacterService.getInstance());
    }
    return field;
  }

  /**
   * Sets the field to not have enemies.
   *
   * @param character that going to explore that field.
   */
  public void initSafeField(Character character) {
    initField(character, false, false);
  }

  /**
   * Sets the field following the options. It can have an Enemy or not.
   *
   * @param character that going to explore that field.
   * @param canHaveEnemy determines if the field can have enemies.
   * @param jump if the character is jumping that means it will have more chance to find an
   *     enemy.
   */
  private void initField(Character character, boolean canHaveEnemy, boolean jump) {
    if (canHaveEnemy) {
      configure(character, encounterService.getEncounter(character, jump));
      checkMapPosition(character.getMapPosition());
    } else {
      configure(character, Optional.empty());
      checkMapPosition(character.getMapPosition());
    }

  }

  /**
   * Rests the field using the given parameters.
   *
   * @param character that is playing the game.
   * @param enemy {@link Optional} of {@link Enemy}, say if the field will have an enemy.
   */
  private void configure(Character character, Optional<Enemy> enemy) {
    this.character = character;
    title = "Snake Way - Field";
    message = String.format("You have arrived to a new field. You have walked %s positions.",
        character.getMapPosition());

    if (enemy.isPresent()) {
      this.enemy = enemy.get();
      options = Arrays.asList("Fight", "Flee", "Exit");
    } else {
      this.enemy = null;
      options = Arrays.asList("Run to the next field", "Jump to shortcut", "Save & exit", "Exit");
    }
  }

  /**
   * Checks if the game has ended, if the character has achieved the end position.
   *
   * @param mapPosition of th character.
   */
  private void checkMapPosition(int mapPosition) {
    if (mapPosition >= END_OF_THE_WAY) {
      out.println("Congratulations!! You have reached King Kai planet!");
    } else {
      this.render();
    }

  }

  /**
   * Moves the character to the next field.
   *
   * @param character to be moved.
   */
  protected void runToFieldWithEnemy(Character character) {
    character.move(1);
    initField(character, true, false);
  }

  /**
   * Jumps the character to the next field.
   *
   * @param character character to be moved.
   */
  protected void jumpToFieldWithEnemy(Character character) {
    character.move(3);
    initField(character, true, true);
  }

  @Override
  public void render() {
    out.println(MENU_SEPARATOR);
    out.println(title);
    out.println(MENU_SEPARATOR);
    out.println(message);
    out.println(LINE_SEPARATOR);
    out.println(character);
    if (enemy != null) {
      out.println(LINE_SEPARATOR);
      out.println("Enemy appeared");
      out.println(LINE_SEPARATOR);
      out.println(enemy);
      out.println(LINE_SEPARATOR);
      printOptions(options);
      encounterRender();
    } else {
      printOptions(options);
      noEncounterRender();
    }

  }

  /**
   * Render the options for a field that have an Enemy.
   */
  private void encounterRender() {
    int option = getOption();
    switch (option) {
      case 0:
        fight();
        break;
      case 1:
        flee();
        break;
      default:
        return;
    }
  }

  /**
   * Render the options for a field with no Enemy.
   */
  private void noEncounterRender() {
    int option = getOption();
    switch (option) {
      case 0:
        runToFieldWithEnemy(character);
        break;
      case 1:
        jumpToFieldWithEnemy(character);
        break;
      case 2:
        save();
        return;
      default:
        return;

    }
  }

  /**
   * Tries to flee with the character. If the character was enable to flee  {@link
   * EncounterService#flee}, nothing will happen. Otherwise it'll trigger the fight operation {@link
   * Field#fight}.
   */
  private void flee() {
    if (encounterService.flee(character, enemy)) {
      printActionResultMessage("Fled!");
      initSafeField(character);
    } else {
      printActionResultMessage("Unable to flee, fighting!!");
      fight();
    }
  }

  /**
   * Creates a fight between the Character and the Enemy. {@link EncounterService#fight}.
   */
  private void fight() {
    checkFightResult(encounterService.fight(character, enemy));
  }

  /**
   * Checks the hits resulted from a fight operation.
   *
   * @param strike if strike > 0, the Enemy was hit, otherwise the character was hit.
   */
  private void checkFightResult(int strike) {
    if (strike > 0) {
      hitEnemy(strike);
    } else {
      hitReceived(strike);
    }
  }

  /**
   * Applies the hit to the character.
   *
   * @param strike taken.
   */
  private void hitReceived(int strike) {
    printActionResultMessage(String.format("Enemy hits you %d points.", Math.abs(strike)));
    if (character.getHealth() <= 0) {
      out.println("Your life has ended.");
      out.println("GAME OVER");
    } else {
      this.render();
    }
  }

  /**
   * Applies the hit to the enemy.
   *
   * @param strike given.
   */
  private void hitEnemy(int strike) {
    printActionResultMessage(String.format("You hit the enemy %d points.", strike));
    if (enemy.getHealth() > 0) {
      this.render();
    } else {
      enemyDefeated();
    }
  }

  /**
   * Checks if the enemy was defeated and if so, gives the experience to the character and checks if
   * the character achieved the proper level to end the game.
   */
  private void enemyDefeated() {
    printActionResultMessage(String
        .format("You defeated the enemy, and gained %d points of experience.",
            enemy.getGivenExperience()));
    character.addExperience(enemy.getGivenExperience());

    if (character.getExperience() >= MAX_EXPERIENCE) {
      printActionResultMessage(
          "Congratulations!! You have reached a proper level, I'll take you to King Kai!");
    } else {
      out.println("Going to next field");
      character.resetHealth();
      runToFieldWithEnemy(character);
    }
  }


  /**
   * Saves the state.
   */
  private void save() {
    characterService.save(character);
    printActionResultMessage("Saved");
  }
}
