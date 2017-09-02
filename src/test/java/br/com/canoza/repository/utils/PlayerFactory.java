package br.com.canoza.repository.utils;

import br.com.canoza.domain.model.Player;
import java.util.Random;

public class PlayerFactory {

  private static final Random random = new Random();

  public static Player generatePlayer() {
    Player player = new Player();
    player.setName("player");
    player.setHealth(random.nextInt(100));
    player.setSpeed(random.nextInt(20));
    player.setStrength(random.nextInt(20));
    player.setExperience(random.nextInt(50));
    player.setMaxHealth(random.nextInt(100));
    return player;
  }

  public static Player basicPlayer() {
    Player player = new Player();
    player.setName("player");
    player.setHealth(100);
    player.setSpeed(100);
    player.setStrength(100);
    player.setExperience(100);
    player.setMaxHealth(100);
    return player;
  }
}
