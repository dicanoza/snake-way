package br.com.canoza.repository;

import static br.com.canoza.repository.CharacterRepository.CHARACTERS_DAT;
import static br.com.canoza.repository.utils.CharacterFactory.generateCharacter;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.spy;

import br.com.canoza.domain.model.Character;
import br.com.canoza.exception.SnakeWayException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;


public class CharacterRepositoryTest {

  private CharacterRepository characterRepository = spy(CharacterRepository.getInstance());

  @Before
  public void reset() throws Exception {
    ObjectOutputStream objectOutputStream =
        new ObjectOutputStream(new FileOutputStream(CHARACTERS_DAT));

    objectOutputStream.writeObject(new HashMap<String, Character>());

    //force context reload
    characterRepository.load("load");
  }


  @Test
  public void createLoadCharacterTest() {
    Character character = generateCharacter();
    characterRepository.create(character);
    Optional<Character> loaded = characterRepository.load(character.getName());
    assertTrue(loaded.isPresent());
    assertEquals(character, loaded.get());

  }

  @Test
  public void saveCharacterTest() {
    Character character = generateCharacter();
    characterRepository.create(character);
    character.setMapPosition(12);
    character.setExperience(150);
    characterRepository.save(character);
    Optional<Character> loaded = characterRepository.load(character.getName());
    assertTrue(loaded.isPresent());
    assertEquals(character, loaded.get());

  }

  @Test(expected = IllegalArgumentException.class)
  public void overwriteCharacterExceptionTest() {
    Character character = generateCharacter();
    character.setName("unique name");
    characterRepository.create(character);
    characterRepository.create(character);
  }


  @Test(expected = IllegalArgumentException.class)
  public void saveCharacterNullName() {
    Character character = generateCharacter();
    character.setName(null);
    characterRepository.save(character);
  }

  @Test(expected = IllegalArgumentException.class)
  public void saveCharacterBlankName() {
    Character character = generateCharacter();
    character.setName("");
    characterRepository.save(character);
  }

  @Test(expected = IllegalArgumentException.class)
  public void saveCharacterInvalidName() {
    Character character = generateCharacter();
    character.setName("Invalid Name");
    characterRepository.save(character);
  }

  @Test
  public void loadInvalidCharacter() {
    Optional<Character> invalid_character = characterRepository.load("invalid character");
    assertFalse(invalid_character.isPresent());
  }

  @Test(expected = SnakeWayException.class)
  public void loadLockedFile() throws IOException, ClassNotFoundException {
    try (FileOutputStream fileOutputStream = new FileOutputStream(CHARACTERS_DAT)) {
      fileOutputStream.getChannel().lock();
      characterRepository.load("name");
    }
  }

  @Test(expected = SnakeWayException.class)
  public void saveWithException() throws IOException, ClassNotFoundException {
    characterRepository.create(generateCharacter());
    doThrow(new IOException()).when(characterRepository).saveState();
    characterRepository.save(generateCharacter());
  }

  @Test(expected = SnakeWayException.class)
  public void createWithException() throws IOException, ClassNotFoundException {
    doThrow(new IOException()).when(characterRepository).saveState();
    characterRepository.create(generateCharacter());
  }
}
