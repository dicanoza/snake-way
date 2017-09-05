package br.com.canoza.service;

import static org.junit.Assert.assertNotNull;

import br.com.canoza.domain.model.Enemy;
import org.junit.Test;

public class EnemyServiceTest {

  EnemyService enemyService = new EnemyService();

  @Test
  public void generateEnemy() {
    Enemy enemy = enemyService.generateEnemy(12);
    assertNotNull(enemy);

    assertNotNull(enemy.getGivenExperience());
    assertNotNull(enemy.getHealth());
    assertNotNull(enemy.getSpeed());
    assertNotNull(enemy.getStrength());

  }

}
