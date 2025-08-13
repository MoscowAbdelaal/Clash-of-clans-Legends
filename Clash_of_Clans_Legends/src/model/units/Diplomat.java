package model.units;

public class Diplomat extends Hero {

    public Diplomat(String name, int maxHp, int maxActions, int range) {
        super(name, HeroType.PACIFIST, maxHp, maxActions, range);
    }
}
