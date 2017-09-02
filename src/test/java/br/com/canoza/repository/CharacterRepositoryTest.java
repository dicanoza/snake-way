package br.com.canoza.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import br.com.canoza.domain.model.Character;
import br.com.canoza.repository.utils.CharacterFactory;
import java.util.Optional;
import org.junit.BeforeClass;
import org.junit.Test;

public class CharacterRepositoryTest {

  private static CharacterRepository characterRepository;

  @BeforeClass
  public static void init() {
    characterRepository = new CharacterRepository();
  }

  @Test
  public void saveCharacterTest() {
    Character character = CharacterFactory.generateCharacter();
    characterRepository.save(character);
    Optional<Character> loaded = characterRepository.load(character.getName());
    assertTrue(loaded.isPresent());
    assertEquals(character, loaded.get());

  }

  @Test(expected = IllegalArgumentException.class)
  public void saveCharacterNullName() {
    Character character = CharacterFactory.generateCharacter();
    character.setName(null);
    characterRepository.save(character);
  }

  @Test(expected = IllegalArgumentException.class)
  public void saveCharacterBlankName() {
    Character character = CharacterFactory.generateCharacter();
    character.setName("");
    characterRepository.save(character);
  }

}
