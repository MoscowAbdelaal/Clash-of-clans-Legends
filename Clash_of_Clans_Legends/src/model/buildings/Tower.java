package model.buildings;

import model.world.Upgradeable;

public class Tower extends Building {

    private int range;
    private boolean canAttack;

    public Tower() {
        super(200);
        this.range = 3;
        this.canAttack = true;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public boolean isCanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getAttackDmg() {
        return 50;
    }

    public void upgrade() throws Exception {
        if (getLevel() >= 3) {
            throw new Exception("Tower has reached the maximum level.");
        }
        setLevel(getLevel() + 1);
        setRange(getRange() + 1);
    }
}
