package br.com.canoza.repository;

import br.com.canoza.domain.model.Player;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static br.com.canoza.utils.Preconditions.checkNotBlank;
import static br.com.canoza.utils.Preconditions.checkNotNull;

public class PlayerRepository {

    public static final String PLAYERS_DAT = "snake-way_players.dat";
    private static Map<String, Player> players = new HashMap<>();

    public Player create(final Player player) {
        checkNotNull(player, "Player");
        checkNotBlank(player.getName(), "Player Name");
        if (players.containsKey(player)) {
            throw new IllegalArgumentException(String.format("Player with the name %s already exists", player.getName()));
        }

        players.put(player.getName(), player);
        saveState();
        return player;
    }

    public Optional<Player> load(String name) {
        loadPlayerState();

        return Optional.ofNullable(players.get(name));
    }

    private void saveState() {
        try (ObjectOutputStream oos =
                     new ObjectOutputStream(new FileOutputStream(PLAYERS_DAT))) {

            oos.writeObject(players);

        } catch (Exception ex) {
            throw new RuntimeException("Could not save Player State", ex);
        }

    }

    private void loadPlayerState() {
        try (ObjectInputStream oos =
                     new ObjectInputStream(new FileInputStream(PLAYERS_DAT))) {

            players = (Map<String, Player>) oos.readObject();

        } catch (Exception ex) {
            throw new RuntimeException("Could not load Player State", ex);
        }
    }
}
