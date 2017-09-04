package br.com.canoza.controller.screen;

import static br.com.canoza.repository.CharacterRepository.CHARACTERS_DAT;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.canoza.domain.model.Character;
import br.com.canoza.service.CharacterService;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LoadGameTest {


  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private MainMenu menu;
  private LoadGame loadGame;
  private NewGame newGame = spy(NewGame.getInstance());
  private Field field;
  private CharacterService characterService;

  @Before
  public void init() throws IOException {
    //Reset File
    ObjectOutputStream objectOutputStream =
        new ObjectOutputStream(new FileOutputStream(CHARACTERS_DAT));
    objectOutputStream.writeObject(new HashMap<String, Character>());

    field = spy(Field.getInstance());
    characterService = spy(CharacterService.getInstance());
    newGame = spy(new NewGame(field, characterService));
    loadGame = spy(new LoadGame(field, characterService));
    menu = spy(new MainMenu(newGame, loadGame));

    MockitoAnnotations.initMocks(this);
      System.setOut(new PrintStream(outContent));
  }

  @Test
  public void saveLoadTest() {
    doReturn("0").when(menu).readString();
    doReturn("player1").doReturn("0").when(newGame).readString();
    doReturn("2").when(field).readString();
    menu.render();
    assertThat(outContent.toString(), containsString("Saved"));

    doReturn("1").when(menu)
        .readString();
    doReturn("player1").when(loadGame).readString();
    doReturn("2").when(field).readString();
    menu.render();

    verify(field, times(2)).render();

  }

  @Test
  public void saveLoadInvalidCharacterTest() {
    doReturn("0").when(menu).readString();
    doReturn("player1").doReturn("0").when(newGame).readString();
    doReturn("2").when(field).readString();
    menu.render();

    doReturn("1").when(menu).readString();
    doReturn("WrongCharacter").doReturn("player1").when(loadGame).readString();
    doReturn("2").when(field).readString();
    menu.render();

    verify(field, times(2)).render();
    verify(loadGame, times(2)).render();
    assertThat(outContent.toString(), containsString("Invalid character"));

  }


}
