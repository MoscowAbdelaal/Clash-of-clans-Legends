package model.units;

import model.world.Upgradeable;

public abstract class SupportUnit extends Unit implements Upgradeable {
	
    private int attackDmg;
    private int level;

    public SupportUnit(int maxHp, int maxActions, int range, int attackDmg) {
        super(maxHp, maxActions, range);
        this.attackDmg = attackDmg;
        this.level = 1;
    }

    public int getAttackDmg() {
        return attackDmg;
    }

    public void setAttackDmg(int attackDmg) {
        this.attackDmg = attackDmg;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level >= 1 && level <= 3) {
            this.level = level;
        }
    }
}
