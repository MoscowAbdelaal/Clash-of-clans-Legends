package model.units;

import model.world.Upgradeable;

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
	public void upgrade() throws Exception{
		if (getLevel() >= 3) 
		{
			throw new Exception("Footman has reached the maximum level.");
		}
		setLevel(getLevel() + 1);
		setMaxHp(getMaxHp() + 50);
		setCurrentHp(getCurrentHp() + 50);
	}
}
