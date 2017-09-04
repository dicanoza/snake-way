package br.com.canoza.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.com.canoza.domain.model.Enemy;
import java.util.Optional;
import org.junit.Test;

public class EnemyServiceTest {

  EnemyService enemyService = new EnemyService();

  @Test
  public void generateEnemy() {
    Optional<Enemy> enemyOptional = enemyService.generateEnemy(12);
    assertNotNull(enemyOptional);
    assertTrue(enemyOptional.isPresent());

    Enemy enemy = enemyOptional.get();
    assertNotNull(enemy.getGivenExperience());
    assertNotNull(enemy.getHealth());
    assertNotNull(enemy.getSpeed());
    assertNotNull(enemy.getStrength());

  }

}
