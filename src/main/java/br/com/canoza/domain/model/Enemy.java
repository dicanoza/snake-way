package br.com.canoza.domain.model;

public class Enemy extends BaseEntity {

  private static final long serialVersionUID = 5820276049021318346L;

  private Integer givenExperience;


  public Integer getGivenExperience() {

    return givenExperience;
  }

  public void setGivenExperience(Integer givenExperience) {
    this.givenExperience = givenExperience;
  }

  @Override
  public String toString() {
    return "Enemy{"
        + "Given Experience=" + givenExperience
        + ", Strength=" + strength
        + ", Speed=" + speed
        + ", Health=" + health
        + '}';
  }

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

    return givenExperience != null ? givenExperience.equals(enemy.givenExperience)
        : enemy.givenExperience == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (givenExperience != null ? givenExperience.hashCode() : 0);
    return result;
  }
}
