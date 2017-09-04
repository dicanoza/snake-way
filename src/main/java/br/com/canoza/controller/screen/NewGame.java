package br.com.canoza.controller.screen;

import static java.lang.System.out;

import br.com.canoza.domain.model.Character;
import br.com.canoza.service.CharacterService;
import java.util.Arrays;
import java.util.Optional;

public class NewGame extends Screen {

  private static NewGame newGame;
  private CharacterService characterService;
  private Field field;

  protected NewGame(Field field, CharacterService characterService) {
    this.field = field;
    this.characterService = characterService;
    title = "Snake Way - New Character";
    message = "This is time for you to start your adventure, let's choose a name for your "
        + "character";
  }

  public static NewGame getInstance() {
    if (newGame == null) {
      newGame = new NewGame(Field.getInstance(), CharacterService.getInstance());
    }
    return newGame;
  }

  @Override
  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    out.println("------------------------------------------------------------");
    out.println("Insert the Character Name:");
    String name = readString();
    Optional<Character> load = characterService.load(name);
    if (load.isPresent()) {
      out.println("------------------------------------------------------------");
      out.println("Character already exists, choose another name");
      render();
    } else {

      Character character = characterService.createCharacter(name);

      out.println("------------------------------------------------------------");
      out.println("This is your Character status");
      out.println(character);

      out.println("------------------------------------------------------------");
      out.println("Do you want to continue?");
      printOptions(Arrays.asList("Yes", "No"));
      if (getOption(1) == 1) {
        render();
      } else {
        characterService.create(character);
        field.initSafeField(character);
      }
    }
  }

}
