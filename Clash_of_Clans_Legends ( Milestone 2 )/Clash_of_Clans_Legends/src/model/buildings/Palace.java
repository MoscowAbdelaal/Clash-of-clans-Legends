package model.buildings;

import model.world.Upgradeable;
import exceptions.InvalidActionException;

public class Palace extends Building {
    public Palace() {
        super(1000);
    }

    public void upgrade() throws InvalidActionException {
        if (getLevel() >= 3) {
            throw new InvalidActionException("Support Units cannot be upgraded above level 3");
        }
        setLevel(getLevel() + 1);
        setMaxHp(getMaxHp() + 500);
        setCurrentHp(getCurrentHp() + 500);
    }
}
