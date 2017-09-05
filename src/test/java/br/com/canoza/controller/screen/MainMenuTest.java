package br.com.canoza.controller.screen;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.canoza.controller.screen.LoadGame;
import br.com.canoza.controller.screen.MainMenu;
import br.com.canoza.controller.screen.NewGame;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainMenuTest{

  @InjectMocks
  private MainMenu menu;

  @Mock
  private NewGame newGame;

  @Mock
  private LoadGame loadGame;

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

  @Before
  public void init() {
    menu = spy(new MainMenu(newGame,loadGame));
    MockitoAnnotations.initMocks(this);
    System.setOut(new PrintStream(outContent));
    System.setErr(new PrintStream(errContent));
  }

  @Test
  public void newGameTest() {
    doReturn("0").when(menu).readString();

    menu.render();

    doNothing().when(newGame).render();
    verify(newGame, times(1)).render();
  }

  @Test
  public void loadGameTest() {

    doReturn("1").when(menu).readString();
    doNothing().when(loadGame).render();

    menu.render();
    verify(loadGame, times(1)).render();
  }

  @Test
  public void invalidOption() {

    doReturn("3").doReturn("asdf").doReturn("0").when(menu).readString();
    doNothing().when(newGame).render();

    menu.render();
    verify(newGame, times(1)).render();
    verify(menu, times(3)).readString();
    assertThat(errContent.toString(), containsString("Invalid options, please insert a valid number"));

  }

  @Test
  public void testInstance(){
    MainMenu instance = MainMenu.getInstance();
    assertNotNull(instance);
    assertTrue(MainMenu.getInstance() == instance);
  }
}
