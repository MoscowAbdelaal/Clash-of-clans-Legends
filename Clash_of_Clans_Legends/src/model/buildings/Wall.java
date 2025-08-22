package model.buildings;

import model.world.Upgradeable;

public class Wall extends Building {
    public Wall() {
        super(500);
    }

    public void upgrade() throws Exception {
        if (getLevel() >= 3) {
            throw new Exception("Wall has reached the maximum level.");
        }
        setLevel(getLevel() + 1);
        setMaxHp(getMaxHp() + 250);
        setCurrentHp(getCurrentHp() + 250);
    }
}
