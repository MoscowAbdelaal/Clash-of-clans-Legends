package model.units;

import model.world.Upgradeable;
import exceptions.*;

public class Cavalry extends SupportUnit {

    public Cavalry() {
        super(150, 3, 1, 30); 
    }
    public void upgrade() throws InvalidActionException {
		if (getLevel() >= 3) 
		{
			throw new InvalidActionException("Support Units cannot be upgraded above level 3");
		}
		setLevel(getLevel() + 1);
		setMaxActions(getMaxActions() + 1);
		setActionsAvailable(getActionsAvailable() + 1);
		setMaxHp(getMaxHp() + 50);
		setCurrentHp(getCurrentHp() + 50);
	}
}
