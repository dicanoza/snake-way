package br.com.canoza.domain.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseEntity)) {
      return false;
    }

    BaseEntity that = (BaseEntity) o;

    if (strength != null ? !strength.equals(that.strength) : that.strength != null) {
      return false;
    }
    if (speed != null ? !speed.equals(that.speed) : that.speed != null) {
      return false;
    }
    return health != null ? health.equals(that.health) : that.health == null;
  }

  @Override
  public int hashCode() {
    int result = strength != null ? strength.hashCode() : 0;
    result = 31 * result + (speed != null ? speed.hashCode() : 0);
    result = 31 * result + (health != null ? health.hashCode() : 0);
    return result;
  }

  private static final long serialVersionUID = 8214044594876696646L;


  protected Integer strength;
  protected Integer speed;
  protected Integer health;


  public Integer getStrength() {
    return strength;
  }

  public void setStrength(Integer strength) {
    this.strength = strength;
  }

  public Integer getSpeed() {
    return speed;
  }

  public void setSpeed(Integer speed) {
    this.speed = speed;
  }

  public Integer getHealth() {
    return health;
  }

  public void setHealth(Integer health) {
    this.health = health;
  }
}
