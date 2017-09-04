package br.com.canoza.controller.screen;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import br.com.canoza.service.CharacterService;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
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
  private CharacterService characterService = spy(CharacterService.getInstance());

  @Before
  public void init() {
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
}
