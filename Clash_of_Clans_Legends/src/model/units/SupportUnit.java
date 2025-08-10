package model.units;

public abstract class SupportUnit extends Unit {
	
    private int attackDmg;

    public SupportUnit(int maxHp, int maxActions, int range, int attackDmg) {
        super(maxHp, maxActions, range);
        this.attackDmg = attackDmg;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = attackDmg;
    }
}
