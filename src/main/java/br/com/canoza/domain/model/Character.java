package br.com.canoza.domain.model;

public class Character extends BaseEntity {

  private static final long serialVersionUID = 8384460808672816945L;

  private Integer maxHealth;
  private Integer experience;
  private Integer mapPosition;
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getMaxHealth() {
    return maxHealth;
  }

  public void setMaxHealth(Integer maxHealth) {
    this.maxHealth = maxHealth;
  }

  public Integer getExperience() {
    return experience;
  }

  public void setExperience(Integer experience) {
    this.experience = experience;
  }

  public Integer getMapPosition() {
    return mapPosition;
  }

  public void setMapPosition(Integer mapPosition) {
    this.mapPosition = mapPosition;
  }


  public void addExperience(Integer givenExperience) {
    this.experience += givenExperience;
  }

  public void resetHealth() {
    this.health = maxHealth;
  }

  public void move(Integer steps) {
    this.mapPosition += steps;

  }


  @Override
  public String toString() {
    return name + "={"
        + "Experience=" + experience
        + ", Map Position=" + mapPosition
        + ", Max Health=" + maxHealth
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
    if (!(o instanceof Character)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }

    Character character = (Character) o;

    if (maxHealth != null ? !maxHealth.equals(character.maxHealth) : character.maxHealth != null) {
      return false;
    }
    if (experience != null ? !experience.equals(character.experience)
        : character.experience != null) {
      return false;
    }
    if (mapPosition != null ? !mapPosition.equals(character.mapPosition)
        : character.mapPosition != null) {
      return false;
    }
    return name != null ? name.equals(character.name) : character.name == null;
  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + (maxHealth != null ? maxHealth.hashCode() : 0);
    result = 31 * result + (experience != null ? experience.hashCode() : 0);
    result = 31 * result + (mapPosition != null ? mapPosition.hashCode() : 0);
    result = 31 * result + (name != null ? name.hashCode() : 0);
    return result;
  }
}
