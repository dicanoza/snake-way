package br.com.canoza.controller;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import br.com.canoza.controller.screen.LoadGame;
import br.com.canoza.controller.screen.MainMenu;
import br.com.canoza.controller.screen.NewGame;
import java.io.ByteArrayInputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MainMenuTest{

  @InjectMocks
  private MainMenu menu;

  @Mock
  private NewGame newGame;

  @Mock
  private LoadGame loadGame;


  @Test
  public void newGameTest() {
    System.setIn(new ByteArrayInputStream("0".getBytes()));
    menu.render();

    doNothing().when(newGame).render();
    verify(newGame, times(1)).render();
  }

  @Test
  public void loadGameTest() {
    System.setIn(new ByteArrayInputStream("1".getBytes()));
    doNothing().when(loadGame).render();

    menu.render();
    verify(loadGame, times(1)).render();
  }

  @Test
  public void testInstance(){
    assertNotNull(MainMenu.getInstance());
  }
}
