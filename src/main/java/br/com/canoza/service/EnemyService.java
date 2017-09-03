package br.com.canoza.service;

import static java.lang.Math.max;

import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import java.util.Optional;
import java.util.Random;

public class EnemyService {
  public static final int MAX_MODIFIER = 30;
  public static final String ENEMY = "Enemy";


  private static final Random random = new Random();


  public Optional<Enemy> generateEnemy(int experience) {
    int max = experience + MAX_MODIFIER;
    Enemy enemy = new Enemy();
    enemy.setName(ENEMY);
    enemy.setSpeed(random.nextInt(max));
    enemy.setHealth(random.nextInt(max));
    enemy.setStrength(random.nextInt(max));
    enemy.setGivenExperience(max(1,random.nextInt(5)));

    return Optional.of(enemy);
  }

}
