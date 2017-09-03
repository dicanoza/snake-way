package br.com.canoza.service;

import static br.com.canoza.repository.utils.CharacterFactory.basicCharacter;
import static br.com.canoza.repository.utils.EnemyFactory.basicEnemy;
import static br.com.canoza.repository.utils.EnemyFactory.strongEnemy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.powermock.api.mockito.PowerMockito.spy;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.powermock.api.support.membermodification.MemberMatcher.method;

import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EncounterService.class})
public class EncounterServiceTest {


  @Test
  public void fightTest() throws Exception {
    final Method luckFactor = method(EncounterService.class, "luckFactor");
    final EncounterService encounterService = spy(EncounterService.getInstance());

    final Character character = basicCharacter();
    Enemy enemy = basicEnemy();

    when(encounterService, luckFactor)
        .withArguments(character.getStrength())
        .thenReturn(character.getStrength());
    when(encounterService, luckFactor)
        .withArguments(enemy.getStrength())
        .thenReturn(enemy.getStrength());

    encounterService.fight(character, enemy);
    assertEquals(0, enemy.getHealth());

    enemy = strongEnemy();
    when(encounterService, luckFactor)
        .withArguments(enemy.getStrength())
        .thenReturn(enemy.getStrength());
    encounterService.fight(character, enemy);
    assertEquals(50, character.getHealth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void fightTestNullEnemy() {
    EncounterService.getInstance().fight(basicCharacter(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fightTestNullCharacter() {
    EncounterService.getInstance().fight(null, basicEnemy());
  }

  @Test
  public void fleeTest() throws Exception {
    final Method luckFactor = method(EncounterService.class, "luckFactor");
    final EncounterService encounterService = spy(EncounterService.getInstance());

    final Character character = basicCharacter();

    final Enemy enemy = new Enemy();
    enemy.setSpeed(30);

    when(encounterService, luckFactor)
        .withArguments(character.getSpeed())
        .thenReturn(character.getSpeed());
    when(encounterService, luckFactor)
        .withArguments(enemy.getSpeed())
        .thenReturn(enemy.getSpeed());

    assertTrue(encounterService.flee(character, enemy));

    enemy.setSpeed(200);
    when(encounterService, luckFactor)
        .withArguments(enemy.getSpeed())
        .thenReturn(enemy.getSpeed());
    assertFalse(encounterService.flee(character, enemy));
  }

  @Test(expected = IllegalArgumentException.class)
  public void fleeTestNullEnemy() {
    EncounterService.getInstance().flee(basicCharacter(), null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void fleeTestNullCharacter() {
    EncounterService.getInstance().flee(null, basicEnemy());
  }


}
