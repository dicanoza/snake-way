package br.com.canoza.domain.model;

public class Enemy extends BaseEntity {

  private static final long serialVersionUID = 5820276049021318346L;

  private int givenExperience;


  public int getGivenExperience() {

    return givenExperience;
  }

  public void setGivenExperience(int givenExperience) {
    this.givenExperience = givenExperience;
  }

  @Override
  public String toString() {
    return "Enemy{" +
        "givenExperience=" + givenExperience +
        ", name='" + name + '\'' +
        ", strength=" + strength +
        ", speed=" + speed +
        ", health=" + health +
        '}';
  }
}
