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

  /**
   * Singleton implementation.
   *
   * @return {@link EncounterService}.
   */
  public static EncounterService getInstance() {
    if (encounterService == null) {
      encounterService = new EncounterService();
    }
    return encounterService;
  }

  /**
   * Emulates a fight between a {@link Character} and an {@link Enemy}. The result is base on a
   * random value and the strength of the entities. The result is calculated Character(strength *
   * luck + strength) - Enemy(strength * luck + strength).
   *
   * @param character to fight.
   * @param enemy to fight.
   * @return the result of the fight.
   */
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

  /**
   * Emulates the {@link Character} trying to flee from the {@link Enemy}. The result is base on a
   * random value and the speed of the entities. The result is calculated Character(speed * luck +
   * speed) - Enemy(speed * luck + speed).
   *
   * @param character who wants to flee.
   * @param enemy who wants to chase.
   * @return boolean saying if the character was able to flee.
   */
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

  /**
   * Based on luck, will determinate if there will be an encounter or not. If the character have
   * jumped it will have more chance to find an enemy.
   *
   * @param character who wants to look for enemies.
   * @param jump factor to increase the probability of finding an enemy.
   * @return {@link Optional} of {@link Enemy}, it will be filled if there will be an enemy.
   */
  public Optional<Enemy> getEncounter(Character character, boolean jump) {
    checkNotNull(character, "Character");
    int randomNumber = randomNumber();
    if (randomNumber <= RUN_ENCOUNTER_PERCENTAGE
        || (jump && randomNumber <= JUMP_ENCOUNTER_PERCENTAGE)) {
      return Optional.of(enemyService.generateEnemy(character.getExperience()));
    }
    return Optional.empty();
  }

  /**
   * Based on a random factor retrieves a new value for an integer.
   *
   * @param variable base of the luck operation.
   * @return Math.round(variable + variable * random.nextFloat()).
   */
  public int luckFactor(int variable) {
    return Math.round(variable + variable * random.nextFloat());
  }

  protected int randomNumber() {
    return new Random().nextInt(BOUND);
  }
}
