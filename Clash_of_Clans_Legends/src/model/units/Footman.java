package model.units;

import model.world.Upgradeable;
import exceptions.*;

public class Footman extends SupportUnit {
	
	private int level;
	
    public Footman() {
        super(200, 2, 1, 25); 
        this.level = 1;
    }

	public int getLevel() {
		return level;
	}

	public void setLevel(int level){
	if (level <= 3 && level >= 1) {
		this.level = level;
		}
	}
	public void upgrade() throws Exception {
		if (getLevel() >= 3) 
		{
			throw new InvalidActionException("Support Units cannot be upgraded above level 3");
		}
		setLevel(getLevel() + 1);
		setMaxHp(getMaxHp() + 50);
		setCurrentHp(getCurrentHp() + 50);
	}
}
