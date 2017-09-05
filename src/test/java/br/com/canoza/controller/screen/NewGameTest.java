package br.com.canoza.controller.screen;

import static br.com.canoza.repository.CharacterRepository.CHARACTERS_DAT;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
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
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NewGameTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
  private NewGame newGame;
  private Field field = spy(Field.getInstance());
  private CharacterService characterService;


  @Before
  public void init() throws IOException {
    //Reset File
    ObjectOutputStream objectOutputStream =
        new ObjectOutputStream(new FileOutputStream(CHARACTERS_DAT));
    objectOutputStream.writeObject(new HashMap<String, Character>());

    characterService = spy(CharacterService.getInstance());
    newGame = spy(new NewGame(field, characterService));

    MockitoAnnotations.initMocks(this);
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));

  }

  @After
  public void close() {
    System.setOut(null);
    System.setErr(null);
  }

  @Test
  public void newGame() {
    doReturn("player1").doReturn("0").doReturn("3").doReturn("2").when(newGame).readString();
    doReturn("2").when(field).readString();

    newGame.render();
    assertThat(outContent.toString(), containsString("Saved"));
  }

  @Test
  public void newGameWithRetry() {
    doReturn("player2").doReturn("1").doReturn("player2").doReturn("0").when(newGame).readString();
    doReturn("2").when(field).readString();

    newGame.render();
    assertThat(outContent.toString(), containsString("Saved"));
    verify(newGame, times(2)).render();
  }


  @Test
  public void newGameWithDuplicatedPlayerName() {
    doReturn("player2").doReturn("0").doReturn("player2").doReturn("player3").doReturn("0")
        .when(newGame).readString();
    doReturn("2").when(field).readString();

    newGame.render();
    newGame.render();
    assertThat(outContent.toString(), containsString("Saved"));
    verify(newGame, times(3)).render();
    verify(field, times(2)).readString();
  }


  @Test
  public void testInstance() {
    NewGame instance = NewGame.getInstance();
    assertNotNull(instance);
    assertTrue(NewGame.getInstance() == instance);
  }
}
