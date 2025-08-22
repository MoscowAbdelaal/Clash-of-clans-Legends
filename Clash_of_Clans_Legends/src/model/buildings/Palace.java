package model.buildings;

import model.world.Upgradeable;

public class Palace extends Building {
    public Palace() {
        super(1000);
    }

    public void upgrade() throws Exception {
        if (getLevel() >= 3) {
            throw new Exception("Palace has reached the maximum level.");
        }
        setLevel(getLevel() + 1);
        setMaxHp(getMaxHp() + 500);
        setCurrentHp(getCurrentHp() + 500);
    }
}
