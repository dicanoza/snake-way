package br.com.canoza.domain.model;

public class Player extends BaseEntity {

  private static final long serialVersionUID = 8384460808672816945L;

  private int maxHealth;
  private int experience;


  public int getMaxHealth() {
    return maxHealth;
  }

  public void setMaxHealth(int maxHealth) {
    this.maxHealth = maxHealth;
  }

  public int getExperience() {
    return experience;
  }

  public void setExperience(int experience) {
    this.experience = experience;
  }

  @Override
  public String toString() {
    return "Player{" +
        "maxHealth=" + maxHealth +
        ", experience=" + experience +
        ", name='" + name + '\'' +
        ", strength=" + strength +
        ", speed=" + speed +
        ", health=" + health +
        '}';
  }

}
