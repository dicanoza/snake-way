package br.com.canoza.service;

import br.com.canoza.domain.model.Enemy;
import br.com.canoza.domain.model.Player;

import static br.com.canoza.utils.Preconditions.checkNotNull;

public class EncounterService {

    public void fight(Player player, Enemy enemy) {
        checkNotNull(player, "Player");
        checkNotNull(enemy, "Enemy");

        if (player.getStrength() > enemy.getStrength()) {
            long demage = player.getStrength() - enemy.getStrength();
            enemy.setHealth(enemy.getHealth() - demage);
        } else {
            long demage = enemy.getStrength() - player.getStrength();
            if (demage == 0) {
                demage++;
            }
            player.setHealth(player.getHealth() - demage);
        }
    }

    public boolean run(Player player, Enemy enemy){
        checkNotNull(player, "Player");
        checkNotNull(enemy, "Enemy");

        if (player.getSpeed() > enemy.getSpeed()) {
            player.setHealth(player.getMaxHealth());
            return true;
        }
        return false;
    }


}
