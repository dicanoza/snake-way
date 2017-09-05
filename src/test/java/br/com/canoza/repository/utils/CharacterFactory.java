package br.com.canoza.repository.utils;

import br.com.canoza.domain.model.Character;
import java.util.Random;

public class CharacterFactory {

  private static final Random random = new Random();

  public static Character generateCharacter() {
    Character character = new Character();
    character.setName("character");
    character.setHealth(random.nextInt(100));
    character.setSpeed(random.nextInt(20));
    character.setStrength(random.nextInt(20));
    character.setExperience(random.nextInt(50));
    character.setMaxHealth(random.nextInt(100));
    character.setMapPosition(0);
    return character;
  }

  public static Character basicCharacter() {
    Character character = new Character();
    character.setName("character");
    character.setHealth(100);
    character.setSpeed(100);
    character.setStrength(100);
    character.setExperience(100);
    character.setMaxHealth(100);
    character.setMapPosition(0);
    return character;
  }
}
