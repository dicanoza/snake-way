package br.com.canoza.service;

import br.com.canoza.domain.model.Player;
import java.util.Random;

public class PlayerService {

  private final Random random = new Random();

  public Player createPlayer(String name) {
    Player player = new Player();
    player.setName(name);
    player.setSpeed(random.nextInt(20));
    player.setStrength(random.nextInt(20));
    player.setMaxHealth(random.nextInt(100));
    player.setHealth(player.getMaxHealth());
    return player;
  }

}
