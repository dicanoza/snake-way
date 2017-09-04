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

  private CharacterRepository() {
  }

  public static CharacterRepository getInstance() {
    if (characterRepository == null) {
      characterRepository = new CharacterRepository();
    }
    return characterRepository;
  }

  protected void saveState() throws IOException {
    try (ObjectOutputStream objectOutputStream =
        new ObjectOutputStream(new FileOutputStream(CHARACTERS_DAT))) {
      objectOutputStream.writeObject(characters);
    }
  }

  private void loadCharacterState() {
    try (ObjectInputStream objectInputStream =
        new ObjectInputStream(new FileInputStream(CHARACTERS_DAT))) {

      characters = (HashMap<String, Character>) objectInputStream.readObject();

    } catch (ClassCastException | ClassNotFoundException | IOException ex) {
      throw new SnakeWayException("Could not load Character State", ex);
    }
  }


  public Character create(final Character character) {
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

    return character;
  }

  public Character save(final Character character) {
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
    return character;
  }

  public Optional<Character> load(String name) {
    loadCharacterState();

    return Optional.ofNullable(characters.get(name));
  }
}
