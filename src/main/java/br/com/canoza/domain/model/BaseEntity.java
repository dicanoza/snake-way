package br.com.canoza.domain.model;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

  private static final long serialVersionUID = 8214044594876696646L;

  protected String name;
  protected long strength;
  protected long speed;
  protected long health;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public long getStrength() {
    return strength;
  }

  public void setStrength(long strength) {
    this.strength = strength;
  }

  public long getSpeed() {
    return speed;
  }

  public void setSpeed(long speed) {
    this.speed = speed;
  }

  public long getHealth() {
    return health;
  }

  public void setHealth(long health) {
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
    result = 31 * result + (int) (strength ^ (strength >>> 32));
    result = 31 * result + (int) (speed ^ (speed >>> 32));
    result = 31 * result + (int) (health ^ (health >>> 32));
    return result;
  }
}
