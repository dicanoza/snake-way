package br.com.canoza.service;

import static br.com.canoza.repository.utils.EnemyFactory.basicEnemy;
import static br.com.canoza.repository.utils.EnemyFactory.strongEnemy;
import static br.com.canoza.repository.utils.PlayerFactory.basicPlayer;
import static br.com.canoza.repository.utils.PlayerFactory.generatePlayer;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.com.canoza.domain.model.Enemy;
import br.com.canoza.domain.model.Player;
import java.util.Optional;
import org.junit.Test;

public class EncounterServiceTest {

  private static EncounterService encounterService = EncounterService.getInstance();

  @Test
  public void fightTest() {
    final Player player = basicPlayer();
    final Enemy enemy = basicEnemy();

    encounterService.fight(player, enemy);
    assertEquals(0, enemy.getHealth());

    encounterService.fight(player, strongEnemy());
    assertEquals(50, player.getHealth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fightTestNullEnemy() {
    encounterService.fight(basicPlayer(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fightTestNullPlayer() {
    encounterService.fight(null, basicEnemy());
  }

  @Test
  public void runTest() {
    final Player player = basicPlayer();

    final Enemy enemy = new Enemy();
    enemy.setSpeed(30);

    assertTrue(encounterService.run(player, enemy));

    enemy.setSpeed(100);
    assertFalse(encounterService.run(player, enemy));
  }

  @Test(expected = IllegalArgumentException.class)
  public void runTestNullEnemy() {
    encounterService.run(basicPlayer(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void runTestNullPlayer() {
    encounterService.run(null, basicEnemy());
  }

  @Test
  public void generateEnemy() {
    Optional<Enemy> enemy = encounterService.generateEnemy(generatePlayer());
    assertNotNull(enemy);
    assertTrue(enemy.isPresent());
  }
}
