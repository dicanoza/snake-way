package br.com.canoza.repository.utils;

import br.com.canoza.domain.model.Player;

import static br.com.canoza.utils.GenerationUtils.random;

public class PlayerFactory {
    public static Player generatePlayer() {
        Player player = new Player();
        player.setName("player");
        player.setHealth(random(100));
        player.setSpeed(random(20));
        player.setStrength(random(20));
        player.setExperience(random(50));
        player.setMaxHealth(random(100));
        return player;
    }
    public static Player basicPlayer(){
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
