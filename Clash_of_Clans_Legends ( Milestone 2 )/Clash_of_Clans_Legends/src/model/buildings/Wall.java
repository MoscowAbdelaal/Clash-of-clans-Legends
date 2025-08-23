package model.buildings;

import model.world.Upgradeable;
import exceptions.InvalidActionException;

public class Wall extends Building {
    public Wall() {
        super(500);
    }

    public void upgrade() throws InvalidActionException {
        if (getLevel() >= 3) {
            throw new InvalidActionException("Support Units cannot be upgraded above level 3");
        }
        setLevel(getLevel() + 1);
        setMaxHp(getMaxHp() + 250);
        setCurrentHp(getCurrentHp() + 250);
    }
}
