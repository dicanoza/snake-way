package br.com.canoza.service;

import static br.com.canoza.repository.utils.CharacterFactory.generateCharacter;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.com.canoza.domain.model.Enemy;
import java.util.Optional;
import org.junit.Test;

public class EnemyServiceTest {
  EnemyService enemyService = new EnemyService();

  @Test
  public void generateEnemy() {
    Optional<Enemy> enemy = enemyService.generateEnemy(12);
    assertNotNull(enemy);
    assertTrue(enemy.isPresent());
  }

}
