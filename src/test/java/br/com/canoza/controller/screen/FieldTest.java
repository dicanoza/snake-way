package br.com.canoza.controller.screen;

import static java.lang.Integer.valueOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import br.com.canoza.domain.model.Character;
import br.com.canoza.domain.model.Enemy;
import br.com.canoza.repository.utils.CharacterFactory;
import br.com.canoza.repository.utils.EnemyFactory;
import br.com.canoza.service.CharacterService;
import br.com.canoza.service.EncounterService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FieldTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private EncounterService encounterService = spy(EncounterService.getInstance());
  private CharacterService characterService = spy(CharacterService.getInstance());
  private Field field;

  @Before
  public void init() {
    field = spy(new Field(encounterService, characterService));

    MockitoAnnotations.initMocks(this);
    System.setOut(new PrintStream(outContent));
  }

  @Test
  public void noEncounterTest() {
    Character character = CharacterFactory.generateCharacter();
    doReturn("3").when(field).readString();
    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));

  }

  @Test
  public void runToFieldNoWithEnemyTest() {
    Character character = CharacterFactory.generateCharacter();
    doReturn("0").doReturn("3").when(field).readString();
    doReturn(Optional.empty()).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());
    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), not(containsString("Enemy")));
    assertEquals(valueOf(1),character.getMapPosition());
  }

  @Test
  public void jumpToFieldNoWithEnemyTest() {
    Character character = CharacterFactory.generateCharacter();
    doReturn("1").doReturn("3").when(field).readString();
    doReturn(Optional.empty()).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());
    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), not(containsString("Enemy")));
  }

  @Test
  public void jumpToFieldNoWithEnemyWinTest() {
    Character character = CharacterFactory.generateCharacter();
    // 7 times to get there
    doReturn("1").doReturn("1").doReturn("1").doReturn("1").doReturn("1").doReturn("1")
        .doReturn("1").when(field)
        .readString();
    doReturn(Optional.empty()).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());
    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), not(containsString("Enemy")));
    assertThat(outContent.toString(), containsString("Congratulations!! You have reached King Kai planet!"));

  }

  @Test
  public void runToFieldWithEnemyFleeSuccessTest() {
    Character character = CharacterFactory.generateCharacter();
    Enemy enemy = EnemyFactory.basicEnemy();
    doReturn("0").doReturn("1").doReturn("3").when(field).readString();
    doReturn(Optional.of(enemy)).doReturn(Optional.empty()).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());
    doReturn(true).when(encounterService).flee(any(Character.class), any(Enemy.class));
    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), containsString("Enemy"));
    assertThat(outContent.toString(), containsString("Fled!"));
  }

  @Test
  public void jumpToFieldWithEnemyFleeSuccessTest() {
    Character character = CharacterFactory.generateCharacter();
    Enemy enemy = EnemyFactory.basicEnemy();
    doReturn("1").doReturn("1").doReturn("3").when(field).readString();
    doReturn(Optional.of(enemy)).doReturn(Optional.empty()).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());
    doReturn(true).when(encounterService).flee(any(Character.class), any(Enemy.class));
    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), containsString("Enemy"));
    assertThat(outContent.toString(), containsString("Fled!"));
  }

  @Test
  public void runToFieldWithEnemyFightHitReceivedStillAliveTest() {
    Character character = CharacterFactory.basicCharacter();
    Enemy enemy = EnemyFactory.strongEnemy();
    doReturn("0").doReturn("0").doReturn("2").when(field).readString();
    doReturn(Optional.of(enemy)).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());

    when(encounterService.luckFactor(character.getStrength())).thenReturn(character.getStrength());
    when(encounterService.luckFactor(enemy.getStrength())).thenReturn(enemy.getStrength());

    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), containsString("Enemy"));
    assertThat(outContent.toString(), containsString("Enemy hits you 50 points."));
    assertEquals(valueOf(50), character.getHealth());
    assertThat(outContent.toString(), containsString("Health=50"));
  }

  @Test
  public void runToFieldWithEnemyFightGameOverTest() {
    Character character = CharacterFactory.basicCharacter();
    Enemy enemy = EnemyFactory.strongEnemy();
    doReturn("0").doReturn("0").doReturn("0").when(field).readString();
    doReturn(Optional.of(enemy)).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());

    when(encounterService.luckFactor(character.getStrength())).thenReturn(character.getStrength());
    when(encounterService.luckFactor(enemy.getStrength())).thenReturn(enemy.getStrength());

    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), containsString("Enemy"));
    assertThat(outContent.toString(), containsString("Enemy hits you 50 points."));
    assertEquals(valueOf(0), character.getHealth());
    assertThat(outContent.toString(), containsString("GAME OVER"));
  }

  @Test
  public void runToFieldWithEnemyFightHitEnemyStillAliveTest() {
    Character character = CharacterFactory.basicCharacter();
    Enemy enemy = EnemyFactory.basicEnemy();
    enemy.setHealth(200);
    doReturn("0").doReturn("0").doReturn("2").when(field).readString();
    doReturn(Optional.of(enemy)).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());

    when(encounterService.luckFactor(character.getStrength())).thenReturn(character.getStrength());
    when(encounterService.luckFactor(enemy.getStrength())).thenReturn(enemy.getStrength());

    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), containsString("Enemy"));
    assertThat(outContent.toString(), containsString("You hit the enemy 50 points."));
    assertEquals(valueOf(150), enemy.getHealth());
    assertThat(outContent.toString(), containsString("Health=150"));
  }

  @Test
  public void runToFieldWithEnemyUnableToFleeFightingTest() {
    Character character = CharacterFactory.basicCharacter();
    Enemy enemy = EnemyFactory.basicEnemy();
    enemy.setHealth(200);
    doReturn("0").doReturn("1").doReturn("2").when(field).readString();
    doReturn(Optional.of(enemy)).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());

    doReturn(false).when(encounterService).flee(any(Character.class), any(Enemy.class));

    when(encounterService.luckFactor(character.getStrength())).thenReturn(character.getStrength());
    when(encounterService.luckFactor(enemy.getStrength())).thenReturn(enemy.getStrength());

    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), containsString("Enemy"));
    assertThat(outContent.toString(), containsString("Unable to flee, fighting!!"));
    assertThat(outContent.toString(), containsString("You hit the enemy 50 points."));
    assertEquals(valueOf(150), enemy.getHealth());
    assertThat(outContent.toString(), containsString("Health=150"));

  }

  @Test
  public void runToFieldWithEnemyFightKillEnemyTest() {
    Character character = CharacterFactory.basicCharacter();
    character.setExperience(1);
    Enemy enemy = EnemyFactory.basicEnemy();

    enemy.setGivenExperience(1);
    doReturn("0").doReturn("0").doReturn("2").when(field).readString();
    doReturn(Optional.of(enemy)).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());

    when(encounterService.luckFactor(character.getStrength())).thenReturn(character.getStrength());
    when(encounterService.luckFactor(enemy.getStrength())).thenReturn(enemy.getStrength());

    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), containsString("Enemy"));
    assertThat(outContent.toString(), containsString("You hit the enemy 50 points."));
    assertEquals(valueOf(0), enemy.getHealth());
    assertThat(outContent.toString(),
        containsString("You defeated the enemy, and gained 1 points of experience."));
  }

  @Test
  public void runToFieldWithEnemyFightKillEnemyWinTest() {
    Character character = CharacterFactory.basicCharacter();
    character.setExperience(1);
    Enemy enemy = EnemyFactory.basicEnemy();

    enemy.setGivenExperience(19);
    doReturn("0").doReturn("0").doReturn("2").when(field).readString();
    doReturn(Optional.of(enemy)).when(encounterService)
        .getEncounter(any(Character.class), anyBoolean());

    when(encounterService.luckFactor(character.getStrength())).thenReturn(character.getStrength());
    when(encounterService.luckFactor(enemy.getStrength())).thenReturn(enemy.getStrength());

    field.initSafeField(character);

    assertThat(outContent.toString(), containsString("Run to the next field"));
    assertThat(outContent.toString(), containsString("Enemy"));
    assertThat(outContent.toString(), containsString("You hit the enemy 50 points."));
    assertEquals(valueOf(0), enemy.getHealth());
    assertThat(outContent.toString(), containsString(
        "Congratulations!! You have reached a proper level, I'll take you to King Kai!"));
  }

  @Test
  public void testInstance(){
    Field instance = Field.getInstance();
    assertNotNull(instance);
    assertTrue(Field.getInstance() == instance);
  }
}
