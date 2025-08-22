package model.units;

import model.world.Upgradeable;

public class Archer extends SupportUnit {

    public Archer() {
        super(100, 2, 3, 35); 
    }
    public void upgrade() throws Exception{
		if (getLevel() >= 3) 
		{
			throw new Exception("Archer has reached the maximum level.");
		}
		setLevel(getLevel() + 1);
		setRange(getRange() + 1);
	}
}
