package br.com.canoza.service;

import br.com.canoza.domain.model.Enemy;
import br.com.canoza.domain.model.Player;

import java.util.Optional;

import static br.com.canoza.utils.GenerationUtils.random;
import static br.com.canoza.utils.Preconditions.checkNotNull;

public class EncounterService {

    public static final int ENCOUNTER_PERCENTAGE = 4;
    public static final int MAX_MODIFIER = 10;

    public void fight(Player player, Enemy enemy) {
        checkNotNull(player, "Player");
        checkNotNull(enemy, "Enemy");

        if (player.getStrength() > enemy.getStrength()) {
            long damage = player.getStrength() - enemy.getStrength();
            enemy.setHealth(enemy.getHealth() - damage);
        } else {
            long damage = enemy.getStrength() - player.getStrength();
            if (damage == 0) {
                damage++;
            }
            player.setHealth(player.getHealth() - damage);
        }
    }

    public boolean run(Player player, Enemy enemy) {
        checkNotNull(player, "Player");
        checkNotNull(enemy, "Enemy");

        if (player.getSpeed() > enemy.getSpeed()) {
            player.setHealth(player.getMaxHealth());
            return true;
        }
        return false;
    }

    public Optional<Enemy> getEncounter(Player player) {

        // 40% of chance to find a monster
        if (random(10) > ENCOUNTER_PERCENTAGE) {
            return Optional.empty();
        }
        return  generateEnemy(player);
    }

    public Optional<Enemy> generateEnemy(Player player){
        long max = player.getExperience() * MAX_MODIFIER;
        Enemy enemy = new Enemy();
        enemy.setName("Enemy");
        enemy.setSpeed(random(max));
        enemy.setHealth(random(max));
        enemy.setStrength(random(max));
        enemy.setGivenExperience(random(max));

        return Optional.of(enemy);
    }
}
