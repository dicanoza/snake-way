package br.com.canoza.service;

import static br.com.canoza.utils.Preconditions.checkNotNull;

import br.com.canoza.domain.model.Enemy;
import br.com.canoza.domain.model.Player;
import java.util.Optional;
import java.util.Random;

public class EncounterService {

  public static final int ENCOUNTER_PERCENTAGE = 4;
  public static final int MAX_MODIFIER = 10;
  public static final String ENEMY = "Enemy";
  public static final String PLAYER = "Player";
  private static final Random random = new Random();

  private static EncounterService encounterService;

  private EncounterService(){
  }

  public static EncounterService getInstance(){
    if(encounterService != null){
      encounterService = new EncounterService();
    }
    return encounterService;
  }

  public void fight(Player player, Enemy enemy) {
    checkNotNull(player, PLAYER);
    checkNotNull(enemy, ENEMY);

    if (player.getStrength() > enemy.getStrength()) {
      int damage = player.getStrength() - enemy.getStrength();
      enemy.setHealth(enemy.getHealth() - damage);
    } else {
      int damage = enemy.getStrength() - player.getStrength();
      if (damage == 0) {
        damage++;
      }
      player.setHealth(player.getHealth() - damage);
    }
  }

  public boolean run(Player player, Enemy enemy) {
    checkNotNull(player, PLAYER);
    checkNotNull(enemy, ENEMY);

    if (player.getSpeed() > enemy.getSpeed()) {
      player.setHealth(player.getMaxHealth());
      return true;
    }
    return false;
  }

  public Optional<Enemy> getEncounter(Player player) {

    // 40% of chance to find a monster
    if (random.nextInt(10) > ENCOUNTER_PERCENTAGE) {
      return Optional.empty();
    }
    return generateEnemy(player);
  }

  public Optional<Enemy> generateEnemy(Player player) {
    int max = player.getExperience() * MAX_MODIFIER;
    Enemy enemy = new Enemy();
    enemy.setName(ENEMY);
    enemy.setSpeed(random.nextInt(max));
    enemy.setHealth(random.nextInt(max));
    enemy.setStrength(random.nextInt(max));
    enemy.setGivenExperience(random.nextInt(max));

    return Optional.of(enemy);
  }
}
