package model.units;

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
}
