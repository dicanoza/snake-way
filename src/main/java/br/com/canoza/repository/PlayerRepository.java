package br.com.canoza.repository;

import static br.com.canoza.utils.Preconditions.checkNotBlank;
import static br.com.canoza.utils.Preconditions.checkNotNull;

import br.com.canoza.domain.model.Player;
import br.com.canoza.exception.SnakeWayException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Optional;

public class PlayerRepository {

  public static final String PLAYERS_DAT = "snake-way_players.dat";
  private static HashMap<String, Player> players = new HashMap<>();

  private static void saveState() {
    try (ObjectOutputStream objectOutputStream =
        new ObjectOutputStream(new FileOutputStream(PLAYERS_DAT))) {

      objectOutputStream.writeObject(players);

    } catch (IOException ex) {
      throw new SnakeWayException("Could not save Player State", ex);
    }

  }

  private static void loadPlayerState() {
    try (ObjectInputStream objectInputStream =
        new ObjectInputStream(new FileInputStream(PLAYERS_DAT))) {

      players = (HashMap<String, Player>) objectInputStream.readObject();

    } catch (ClassNotFoundException | IOException ex) {
      throw new SnakeWayException("Could not load Player State", ex);
    }
  }

  public Player create(final Player player) {
    checkNotNull(player, "Player");
    checkNotBlank(player.getName(), "Player Name");
    if (players.containsKey(player)) {
      throw new IllegalArgumentException(
          String.format("Player with the name %s already exists", player.getName()));
    }

    players.put(player.getName(), player);
    saveState();
    return player;
  }

  public Optional<Player> load(String name) {
    loadPlayerState();

    return Optional.ofNullable(players.get(name));
  }
}
