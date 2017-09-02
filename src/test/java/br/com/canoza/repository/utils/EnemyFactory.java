package br.com.canoza.repository.utils;

import br.com.canoza.domain.model.Enemy;

public class EnemyFactory {

  public static Enemy basicEnemy() {
    return createEnemy(50, "Saibaman");
  }

  public static Enemy strongEnemy() {
    return createEnemy(150, "Vegeta");

  }

  private static Enemy createEnemy(int value, String name) {
    Enemy enemy = new Enemy();
    enemy.setName(name);
    enemy.setSpeed(value);
    enemy.setHealth(value);
    enemy.setStrength(value);
    enemy.setGivenExperience(value);
    return enemy;
  }
}
