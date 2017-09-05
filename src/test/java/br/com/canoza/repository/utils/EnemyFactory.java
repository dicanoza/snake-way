package br.com.canoza.repository.utils;

import br.com.canoza.domain.model.Enemy;

public class EnemyFactory {

  public static Enemy basicEnemy() {
    return createEnemy(50);
  }

  public static Enemy strongEnemy() {
    return createEnemy(150);

  }

  private static Enemy createEnemy(int value) {
    Enemy enemy = new Enemy();
    enemy.setSpeed(value);
    enemy.setHealth(value);
    enemy.setStrength(value);
    enemy.setGivenExperience(value);
    return enemy;
  }
}
