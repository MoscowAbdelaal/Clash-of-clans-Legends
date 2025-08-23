package model.buildings;

import model.world.Upgradeable;
import exceptions.InvalidActionException;

public abstract class Building implements Upgradeable {
    private int maxHp;
    private int currentHp;
    private int level;

    public Building(int maxHp) {
        this.maxHp = maxHp;
        currentHp = maxHp;
        level = 1;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
        if (currentHp < 0) {
            this.currentHp = 0;
        } else if (currentHp > maxHp) {
            this.currentHp = maxHp;
        } else {
            this.currentHp = currentHp;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttackDmg() {
        return 0;
    }

    public int getRange() {
        return 0;
    }

    public void upgrade() throws InvalidActionException {
        if (getLevel() >= 3) {
            throw new InvalidActionException("Support Units cannot be upgraded above level 3");
        }
        setLevel(getLevel() + 1);
    }
}
