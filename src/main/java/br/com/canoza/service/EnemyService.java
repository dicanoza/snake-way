package br.com.canoza.service;

import static java.lang.Math.max;

import br.com.canoza.domain.model.Enemy;
import java.util.Random;

public class EnemyService {

  public static final int MAX_MODIFIER = 30;
  private static final Random random = new Random();

  /**
   * Generates a new {@link Enemy} based on random values. The experience of the {@link Character}
   * will influence the result, with more experience higher the chances to find a stronger {@link
   * Enemy}.
   *
   * @param experience to influence the modifiers of the {@link Enemy}.
   */
  public Enemy generateEnemy(int experience) {
    int max = experience + MAX_MODIFIER;
    Enemy enemy = new Enemy();
    enemy.setSpeed(random.nextInt(max));
    enemy.setHealth(random.nextInt(max));
    enemy.setStrength(random.nextInt(max));
    enemy.setGivenExperience(max(1, random.nextInt(5)));

    return enemy;
  }

}
