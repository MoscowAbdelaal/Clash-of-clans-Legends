package model.units;

public class Monk extends Hero {

    public Monk(String name, int maxHp, int maxActions, int range) {
        super(name, HeroType.PACIFIST, maxHp, maxActions, range);
    }
    public void useSpecial(SupportUnit u) throws Exception {
        u.setCurrentHp(u.getMaxHp());
        setSpecialActionCooldown(2);
    }
}
