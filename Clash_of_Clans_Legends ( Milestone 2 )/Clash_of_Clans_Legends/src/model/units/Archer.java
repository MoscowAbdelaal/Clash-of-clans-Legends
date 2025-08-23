package model.units;

import model.world.Upgradeable;
import exceptions.*;

public class Archer extends SupportUnit {

    public Archer() {
        super(100, 2, 3, 35); 
    }
    public void upgrade() throws InvalidActionException {
		if (getLevel() >= 3) 
		{
			throw new InvalidActionException("Support Units cannot be upgraded above level 3");
		}
		setLevel(getLevel() + 1);
		setRange(getRange() + 1);
	}
}
