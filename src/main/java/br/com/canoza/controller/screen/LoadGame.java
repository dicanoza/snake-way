package br.com.canoza.controller.screen;

import static br.com.canoza.controller.screen.Field.initSafeField;
import static java.lang.System.out;

import br.com.canoza.controller.engine.GameEngine;
import br.com.canoza.domain.model.Character;
import br.com.canoza.service.CharacterService;
import java.util.Optional;

public class LoadGame extends Screen {


  private CharacterService characterService = new CharacterService();

  public LoadGame() {
    title = "Load Game";
    message = "Please insert the name of your character";
  }


  @Override
  public void render() {
    out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    out.println(title);
    out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    out.println(message);
    String name = GameEngine.readString();
    Optional<Character> character = characterService.load(name);
    if(character.isPresent()){
      initSafeField(character.get());
    }else{
      GameEngine.printActionResultMessage("Invalid character, try another one");
      this.render();
    }
  }
}
