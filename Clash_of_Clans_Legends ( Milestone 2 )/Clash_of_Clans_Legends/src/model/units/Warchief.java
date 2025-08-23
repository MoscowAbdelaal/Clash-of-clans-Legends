package model.units;

public class Warchief extends Hero {
    
	private int attackDmg;

    public Warchief(String name, int maxHp, int maxActions, int range, int attackDmg) {
        super(name, HeroType.AGGRESSOR, maxHp, maxActions, range);
        this.attackDmg = attackDmg;
    }

    public int getAttackDmg() {
        return attackDmg;
    }
    public void useSpecial(SupportUnit u) throws Exception {
        u.upgrade();
        u.upgrade();
        setSpecialActionCooldown(3);
    }
}
