package br.com.canoza.service;

import static br.com.canoza.repository.utils.CharacterFactory.basicCharacter;
import static br.com.canoza.repository.utils.CharacterFactory.generateCharacter;
import static br.com.canoza.repository.utils.EnemyFactory.basicEnemy;
import static br.com.canoza.repository.utils.EnemyFactory.strongEnemy;
import static java.lang.Integer.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import java.util.Optional;
import org.junit.Test;

public class EncounterServiceTest {

  final EncounterService encounterService = spy(EncounterService.getInstance());

  @Test
  public void fightTest() throws Exception {
    final Character character = basicCharacter();
    Enemy enemy = basicEnemy();

    when(encounterService.luckFactor(character.getStrength())).thenReturn(character.getStrength());
    when(encounterService.luckFactor(enemy.getStrength())).thenReturn(enemy.getStrength());

    encounterService.fight(character, enemy);
    assertEquals(valueOf(0), enemy.getHealth());

    enemy = strongEnemy();
    when(encounterService.luckFactor(enemy.getStrength())).thenReturn(enemy.getStrength());
    encounterService.fight(character, enemy);
    assertEquals(valueOf(50), character.getHealth());
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
  public void fleeTest() throws Exception {
    final Character character = basicCharacter();

    final Enemy enemy = new Enemy();
    enemy.setSpeed(30);

    when(encounterService.luckFactor(character.getSpeed())).thenReturn(character.getSpeed());
    when(encounterService.luckFactor(enemy.getSpeed())).thenReturn(enemy.getSpeed());
    assertTrue(encounterService.flee(character, enemy));

    enemy.setSpeed(200);

    when(encounterService.luckFactor(enemy.getSpeed())).thenReturn(enemy.getSpeed());
    assertFalse(encounterService.flee(character, enemy));
  }

  @Test(expected = IllegalArgumentException.class)
  public void fleeTestNullEnemy() {
    encounterService.flee(basicCharacter(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fleeTestNullCharacter() {
    encounterService.flee(null, basicEnemy());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getEncounterNullCharacter() {
    encounterService.getEncounter(null, false);
  }

  @Test
  public void getEncounter() {
    when(encounterService.randomNumber()).thenReturn(4);
    Optional<Enemy> encounter = encounterService.getEncounter(generateCharacter(), false);
    assertTrue(encounter.isPresent());

    when(encounterService.randomNumber()).thenReturn(6);
    encounter = encounterService.getEncounter(generateCharacter(), false);
    assertFalse(encounter.isPresent());

    when(encounterService.randomNumber()).thenReturn(6);
    encounter = encounterService.getEncounter(generateCharacter(), true);
    assertTrue(encounter.isPresent());

    when(encounterService.randomNumber()).thenReturn(7);
    encounter = encounterService.getEncounter(generateCharacter(), true);
    assertFalse(encounter.isPresent());

  }

}
