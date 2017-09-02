package br.com.canoza.domain.model;

public class Enemy extends BaseEntity {

  private static final long serialVersionUID = 5820276049021318346L;

  private int givenExperience;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Enemy)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    Enemy enemy = (Enemy) o;

    return givenExperience == enemy.givenExperience;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + givenExperience;
    return result;
  }

  public int getGivenExperience() {

    return givenExperience;
  }

  public void setGivenExperience(int givenExperience) {
    this.givenExperience = givenExperience;
  }
}
