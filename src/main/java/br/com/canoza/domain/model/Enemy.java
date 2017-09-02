package br.com.canoza.domain.model;

public class Enemy extends BaseEntity {

    private static final long serialVersionUID = 5820276049021318346L;

    private long givenExperience;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Enemy)) return false;
        if (!super.equals(o)) return false;

        Enemy enemy = (Enemy) o;

        return givenExperience == enemy.givenExperience;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (givenExperience ^ (givenExperience >>> 32));
        return result;
    }
}
