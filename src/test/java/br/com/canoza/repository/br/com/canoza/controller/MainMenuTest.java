package br.com.canoza.repository.br.com.canoza.controller;

import static org.junit.Assert.assertNotNull;

import br.com.canoza.controller.screen.MainMenu;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MainMenuTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));

  }

  @After
  public void cleanUpStreams() {
    System.setOut(null);
    System.setErr(null);
    System.setIn(null);
  }

  @Test
  public void startNewGameChooseCharacterNameAndExit() throws IOException {

    System.setIn(new ByteArrayInputStream("0 \n MyName \n 0 \n 3".getBytes()));

    MainMenu menu = MainMenu.getInstance();
    assertNotNull(menu);
    menu.render();

  }
}
