package model.units;

import model.world.Upgradeable;

public class Cavalry extends SupportUnit {

    public Cavalry() {
        super(150, 3, 1, 30); 
    }
    public void upgrade() throws Exception{
		if (getLevel() >= 3) 
		{
			throw new Exception("Cavalry has reached the maximum level.");
		}
		setLevel(getLevel() + 1);
		setMaxActions(getMaxActions() + 1);
		setActionsAvailable(getActionsAvailable() + 1);
	}
}
