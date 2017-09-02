package br.com.canoza.service;

import static br.com.canoza.repository.utils.EnemyFactory.basicEnemy;
import static br.com.canoza.repository.utils.EnemyFactory.strongEnemy;
import static br.com.canoza.repository.utils.CharacterFactory.basicCharacter;
import static br.com.canoza.repository.utils.CharacterFactory.generateCharacter;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import br.com.canoza.domain.model.Enemy;
import br.com.canoza.domain.model.Character;
import java.util.Optional;
import org.junit.Test;

public class EncounterServiceTest {

  private static EncounterService encounterService = EncounterService.getInstance();

  @Test
  public void fightTest() {
    final Character character = basicCharacter();
    final Enemy enemy = basicEnemy();

    encounterService.fight(character, enemy);
    assertEquals(0, enemy.getHealth());

    encounterService.fight(character, strongEnemy());
    assertEquals(50, character.getHealth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fightTestNullEnemy() {
    encounterService.fight(basicCharacter(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fightTestNullCharacter() {
    encounterService.fight(null, basicEnemy());
  }

  @Test
  public void runTest() {
    final Character character = basicCharacter();

    final Enemy enemy = new Enemy();
    enemy.setSpeed(30);

    assertTrue(encounterService.run(character, enemy));

    enemy.setSpeed(100);
    assertFalse(encounterService.run(character, enemy));
  }

  @Test(expected = IllegalArgumentException.class)
  public void runTestNullEnemy() {
    encounterService.run(basicCharacter(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void runTestNullCharacter() {
    encounterService.run(null, basicEnemy());
  }

  @Test
  public void generateEnemy() {
    Optional<Enemy> enemy = encounterService.generateEnemy(generateCharacter());
    assertNotNull(enemy);
    assertTrue(enemy.isPresent());
  }
}
