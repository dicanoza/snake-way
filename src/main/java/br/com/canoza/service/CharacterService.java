package br.com.canoza.service;

import br.com.canoza.domain.model.Character;
import java.util.Random;

public class CharacterService {

  private final Random random = new Random();

  public Character createCharacter(String name) {
    Character character = new Character();
    character.setName(name);
    character.setSpeed(random.nextInt(20));
    character.setStrength(random.nextInt(20));
    character.setMaxHealth(random.nextInt(100));
    character.setHealth(character.getMaxHealth());
    return character;
  }

}
