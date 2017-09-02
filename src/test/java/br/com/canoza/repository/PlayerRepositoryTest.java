package br.com.canoza.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import br.com.canoza.domain.model.Player;
import br.com.canoza.repository.utils.PlayerFactory;
import java.util.Optional;
import org.junit.BeforeClass;
import org.junit.Test;

public class PlayerRepositoryTest {

  private static PlayerRepository playerRepository;

  @BeforeClass
  public static void init() {
    playerRepository = new PlayerRepository();
  }

  @Test
  public void savePlayerTest() {
    Player player = PlayerFactory.generatePlayer();
    playerRepository.create(player);
    Optional<Player> loaded = playerRepository.load(player.getName());
    assertTrue(loaded.isPresent());
    assertEquals(player, loaded.get());

  }

  @Test(expected = IllegalArgumentException.class)
  public void savePlayerNullName() {
    Player player = PlayerFactory.generatePlayer();
    player.setName(null);
    playerRepository.create(player);
  }

  @Test(expected = IllegalArgumentException.class)
  public void savePlayerBlankName() {
    Player player = PlayerFactory.generatePlayer();
    player.setName("");
    playerRepository.create(player);
  }

}
