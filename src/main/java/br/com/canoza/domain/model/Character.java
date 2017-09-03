package br.com.canoza.domain.model;

public class Character extends BaseEntity {

  private static final long serialVersionUID = 8384460808672816945L;

  private int maxHealth;
  private int experience;
  private int mapPosition;

  @Override
  public String toString() {
    return name + "={" +
        "Experience=" + experience +
        ", Map Position=" + mapPosition +
        ", Max Health=" + maxHealth +
        ", Strength=" + strength +
        ", Speed=" + speed +
        ", Health=" + health +
        '}';
  }

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

  public int getMapPosition() {
    return mapPosition;
  }

  public void setMapPosition(int mapPosition) {
    this.mapPosition = mapPosition;
  }


  public void addExperience(int givenExperience) {
    this.experience += givenExperience;
  }

  public void resetHealth() {
    this.health = maxHealth;
  }

  public void move(int steps) {
    this.mapPosition += steps;

  }
}
