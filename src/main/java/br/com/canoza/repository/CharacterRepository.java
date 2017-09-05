package br.com.canoza.repository;

import static br.com.canoza.utils.Preconditions.checkNotBlank;
import static br.com.canoza.utils.Preconditions.checkNotNull;

import br.com.canoza.domain.model.Character;
import br.com.canoza.exception.SnakeWayException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Optional;

public class CharacterRepository {

  public static final String CHARACTERS_DAT = "snake-way_characters.dat";
  private static CharacterRepository characterRepository;
  private HashMap<String, Character> characters = new HashMap<>();

  /**
   * Class to manipulate persistent data of Characters.
   */
  private CharacterRepository() {
  }

  /**
   * Singleton implementation.
   *
   * @return an instance of {@link CharacterRepository}.
   */
  public static CharacterRepository getInstance() {
    if (characterRepository == null) {
      characterRepository = new CharacterRepository();
    }
    return characterRepository;
  }

  /**
   * Saves the state of to disk.
   *
   * @throws IOException if something goes wrong when writhing data to the disk.
   */
  protected void saveState() throws IOException {
    try (ObjectOutputStream objectOutputStream =
        new ObjectOutputStream(new FileOutputStream(CHARACTERS_DAT))) {
      objectOutputStream.writeObject(characters);
    }
  }

  /**
   * Loads state from disk.
   */
  private void loadCharacterState() {
    try (ObjectInputStream objectInputStream =
        new ObjectInputStream(new FileInputStream(CHARACTERS_DAT))) {

      characters = (HashMap<String, Character>) objectInputStream.readObject();

    } catch (ClassCastException | ClassNotFoundException | IOException ex) {
      throw new SnakeWayException("Could not load Character State", ex);
    }
  }

  /**
   * Inserts the new character into the context and save it to the disk.
   *
   * @param character a {@link Character}.
   */
  public void create(final Character character) {
    checkNotNull(character, "Character");
    checkNotBlank(character.getName(), "Character Name");
    if (characters.containsKey(character.getName())) {
      throw new IllegalArgumentException(
          String.format("Character with the name %s already exists", character.getName()));
    }

    characters.put(character.getName(), character);

    try {
      saveState();
    } catch (IOException ex) {
      throw new SnakeWayException("Could not save Character State", ex);
    }

  }

  /**
   * Save the state of an existing {@link Character}.
   *
   * @param character existing {@link Character} to be saved.
   */
  public void save(final Character character) {
    checkNotNull(character, "Character");
    checkNotBlank(character.getName(), "Character Name");
    if (!characters.containsKey(character.getName())) {
      throw new IllegalArgumentException(
          String.format("Character with the name %s does not exists", character.getName()));
    }

    characters.put(character.getName(), character);

    try {
      saveState();
    } catch (IOException ex) {
      throw new SnakeWayException("Could not save Character State", ex);
    }
  }

  /**
   * Loads the data of one {@link Character}.
   *
   * @param name the name of the character.
   * @return {@link Optional}, will be empty if the no character with the given nave was found.
   */
  public Optional<Character> load(String name) {
    loadCharacterState();

    return Optional.ofNullable(characters.get(name));
  }

}
