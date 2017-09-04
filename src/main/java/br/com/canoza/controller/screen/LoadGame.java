package br.com.canoza.controller.screen;

import static java.lang.System.out;

import br.com.canoza.domain.model.Character;
import br.com.canoza.service.CharacterService;
import java.util.Optional;

public class LoadGame extends Screen {


  private static LoadGame loadGame;
  private CharacterService characterService;
  private Field field;

  LoadGame(Field field, CharacterService characterService) {
    this.field = field;
    this.characterService = characterService;
    title = "Load Game";
    message = "Please insert the name of your character";
  }

  public static LoadGame getInstance() {
    if (loadGame == null) {
      loadGame = new LoadGame(Field.getInstance(), CharacterService.getInstance());
    }
    return loadGame;
  }

  @Override
  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    String name = readString();
    Optional<Character> character = characterService.load(name);
    if (character.isPresent()) {
      field.initSafeField(character.get());
    } else {
      printActionResultMessage("Invalid character, try another one");
      this.render();
    }
  }
}
