package br.com.canoza.service;

import br.com.canoza.domain.model.Character;
import br.com.canoza.repository.CharacterRepository;
import java.util.Optional;
import java.util.Random;

/**
 * Service layer to manipulate {@link Character} business logic.
 */
public class CharacterService {

  private static CharacterService characterService;
  private final Random random = new Random();
  private final CharacterRepository characterRepository;


  private CharacterService(CharacterRepository characterRepository) {
    this.characterRepository = characterRepository;
  }

  /**
   * Singleton implementation.
   *
   * @return {@link CharacterService} instance.
   */
  public static CharacterService getInstance() {
    if (characterService == null) {
      characterService = new CharacterService(CharacterRepository.getInstance());
    }
    return characterService;
  }


  /**
   * Generates a {@link Character} all the fields. This method is used at the new game screen.
   *
   * @param name of the new character.
   */
  public Character createCharacter(String name) {
    Character character = new Character();
    character.setName(name);
    character.setSpeed(random.nextInt(20));
    character.setStrength(random.nextInt(20));
    character.setMaxHealth(random.nextInt(100));
    character.setMapPosition(0);
    character.setExperience(1);
    character.setHealth(character.getMaxHealth());
    return character;
  }

  /**
   * Calls {@link CharacterRepository} to save a {@link Character}.
   *
   * @param character to be saved.
   */
  public void save(Character character) {
    characterRepository.save(character);
  }

  /**
   * Calls {@link CharacterRepository} to load a {@link Character}.
   *
   * @param name name of the character to be loaded.
   * @return {@link Optional}, will be empty if the no character with the given nave was found.
   */
  public Optional<Character> load(String name) {
    return characterRepository.load(name);
  }

  /**
   * Calls {@link CharacterRepository} to create a new {@link Character}.
   *
   * @param character a {@link Character}.
   */
  public void create(Character character) {
    characterRepository.create(character);
  }

}
