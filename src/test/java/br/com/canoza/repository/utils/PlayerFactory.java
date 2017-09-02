package br.com.canoza.repository.utils;

import br.com.canoza.domain.model.Player;

import java.util.stream.Stream;

public class PlayerFactory {
    public static Player generatePlayer(){
        Player player = new Player();
        player.setName("player");
        player.setHealth(random(100));
        player.setSpeed(random(20));
        player.setStrength(random(20));
        player.setExperience(random(50));
        player.setMaxHealth(random(100));
        return player;
    }

    private static int random(final int maxValue){
        return (int) (Math.random() * maxValue);
    }
}
