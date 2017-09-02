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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Player)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    Player player = (Player) o;

    if (maxHealth != player.maxHealth) {
      return false;
    }
    return experience == player.experience;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + maxHealth;
    result = 31 * result + experience;
    return result;
  }
}
