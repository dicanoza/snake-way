package br.com.canoza.service;

import br.com.canoza.domain.model.Character;
import br.com.canoza.repository.CharacterRepository;
import java.util.Optional;
import java.util.Random;

public class CharacterService {

  private final Random random = new Random();
  private static final CharacterRepository characterRepository= new CharacterRepository();

  public Character createCharacter(String name) {
    Character character = new Character();
    character.setName(name);
    character.setSpeed(random.nextInt(20));
    character.setStrength(random.nextInt(20));
    character.setMaxHealth(random.nextInt(100));
    character.setExperience(1);
    character.setHealth(character.getMaxHealth());
    return character;
  }

  public void save(Character character){
    characterRepository.save(character);
  }

  public Optional<Character> load(String character){
    return characterRepository.load(character);
  }

}
