package model.buildings;

import model.world.Upgradeable;
import exceptions.InvalidActionException;

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

    public void upgrade() throws InvalidActionException {
        if (getLevel() >= 3) {
            throw new InvalidActionException("Support Units cannot be upgraded above level 3");
        }
        setLevel(getLevel() + 1);
        setRange(getRange() + 1);
    }
}
