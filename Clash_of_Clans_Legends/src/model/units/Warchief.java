package model.units;

public class Warchief extends Hero {
    
	private final int attackDmg;

    public Warchief(String name, int maxHp, int maxActions, int range, int attackDmg) {
        super(name, HeroType.AGGRESSOR, maxHp, maxActions, range);
        this.attackDmg = attackDmg;
    }

    public int getAttackDmg() {
        return attackDmg;
    }
}
