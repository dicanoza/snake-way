package br.com.canoza.service;

import static br.com.canoza.utils.Preconditions.checkNotNull;

import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import java.util.Optional;
import java.util.Random;

public class EncounterService {

  public static final int RUN_ENCOUNTER_PERCENTAGE = 4;
  public static final int JUMP_ENCOUNTER_PERCENTAGE = 6;
  public static final String ENEMY = "Enemy";
  public static final String CHARACTER = "Character";
  private static final int BOUND = 10;
  private static final Random random = new Random();
  private static EnemyService enemyService = new EnemyService();
  private static EncounterService encounterService;

  private EncounterService() {
  }

  public static EncounterService getInstance() {
    if (encounterService == null) {
      encounterService = new EncounterService();
    }
    return encounterService;
  }

  public int fight(Character character, Enemy enemy) {
    checkNotNull(character, CHARACTER);
    checkNotNull(enemy, ENEMY);

    int characterStrength = luckFactor(character.getStrength());
    int enemyStrength = luckFactor(enemy.getStrength());
    int strike = characterStrength - enemyStrength;

    if (strike > 0) {
      enemy.setHealth(enemy.getHealth() - strike);
    } else {
      character.setHealth(character.getHealth() + strike);
    }
    return strike;
  }

  public boolean flee(Character character, Enemy enemy) {
    checkNotNull(character, CHARACTER);
    checkNotNull(enemy, ENEMY);

    int characterSpeed = luckFactor(character.getSpeed());
    int enemySpeed = luckFactor(enemy.getSpeed());
    int fleeFactor = characterSpeed - enemySpeed;

    if (fleeFactor >= 0) {
      character.setHealth(character.getMaxHealth());
      return true;
    }
    return false;
  }

  public Optional<Enemy> getEncounter(Character character, boolean jump) {
    checkNotNull(character,"Character");
    int randomNumber = randomNumber();
    if (randomNumber <= RUN_ENCOUNTER_PERCENTAGE
        || (jump && randomNumber <= JUMP_ENCOUNTER_PERCENTAGE)) {
      return enemyService.generateEnemy(character.getExperience());
    }
    return Optional.empty();
  }

  public int luckFactor(int variable) {
    return Math.round(variable + variable * random.nextFloat());
  }

  protected int randomNumber() {
    return new Random().nextInt(BOUND);
  }
}
