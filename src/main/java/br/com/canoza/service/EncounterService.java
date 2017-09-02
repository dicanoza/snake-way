package br.com.canoza.service;

import static br.com.canoza.utils.Preconditions.checkNotNull;

import br.com.canoza.domain.model.Enemy;
import br.com.canoza.domain.model.Character;
import java.util.Optional;
import java.util.Random;

public class EncounterService {

  public static final int ENCOUNTER_PERCENTAGE = 4;
  public static final int MAX_MODIFIER = 10;
  public static final String ENEMY = "Enemy";
  public static final String CHARACTER = "Character";
  private static final Random random = new Random();

  private static EncounterService encounterService;

  private EncounterService(){
  }

  public static EncounterService getInstance(){
    if(encounterService == null){
      encounterService = new EncounterService();
    }
    return encounterService;
  }

  public void fight(Character character, Enemy enemy) {
    checkNotNull(character, CHARACTER);
    checkNotNull(enemy, ENEMY);

    if (character.getStrength() > enemy.getStrength()) {
      int damage = character.getStrength() - enemy.getStrength();
      enemy.setHealth(enemy.getHealth() - damage);
    } else {
      int damage = enemy.getStrength() - character.getStrength();
      if (damage == 0) {
        damage++;
      }
      character.setHealth(character.getHealth() - damage);
    }
  }

  public boolean run(Character character, Enemy enemy) {
    checkNotNull(character, CHARACTER);
    checkNotNull(enemy, ENEMY);

    if (character.getSpeed() > enemy.getSpeed()) {
      character.setHealth(character.getMaxHealth());
      return true;
    }
    return false;
  }

  public Optional<Enemy> getEncounter(Character character) {

    // 40% of chance to find a monster
    if (random.nextInt(10) > ENCOUNTER_PERCENTAGE) {
      return Optional.empty();
    }
    return generateEnemy(character);
  }

  public Optional<Enemy> generateEnemy(Character character) {
    int max = character.getExperience() * MAX_MODIFIER;
    Enemy enemy = new Enemy();
    enemy.setName(ENEMY);
    enemy.setSpeed(random.nextInt(max));
    enemy.setHealth(random.nextInt(max));
    enemy.setStrength(random.nextInt(max));
    enemy.setGivenExperience(random.nextInt(max));

    return Optional.of(enemy);
  }
}
