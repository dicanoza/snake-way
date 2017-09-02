package br.com.canoza.domain.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 8214044594876696646L;

  protected String name;
  protected int strength;
  protected int speed;
  protected int health;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getStrength() {
    return strength;
  }

  public void setStrength(int strength) {
    this.strength = strength;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof BaseEntity)) {
      return false;
    }

    BaseEntity that = (BaseEntity) o;

    if (strength != that.strength) {
      return false;
    }
    if (speed != that.speed) {
      return false;
    }
    if (health != that.health) {
      return false;
    }
    return name != null ? name.equals(that.name) : that.name == null;
  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + strength;
    result = 31 * result + speed;
    result = 31 * result + health;
    return result;
  }
}
