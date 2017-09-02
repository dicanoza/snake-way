package br.com.canoza.domain.model;

public class Player extends BaseEntity {

  private static final long serialVersionUID = 8384460808672816945L;

  private long maxHealth;
  private long experience;


  public long getMaxHealth() {
    return maxHealth;
  }

  public void setMaxHealth(long maxHealth) {
    this.maxHealth = maxHealth;
  }

  public long getExperience() {
    return experience;
  }

  public void setExperience(long experience) {
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
    result = 31 * result + (int) (maxHealth ^ (maxHealth >>> 32));
    result = 31 * result + (int) (experience ^ (experience >>> 32));
    return result;
  }
}
