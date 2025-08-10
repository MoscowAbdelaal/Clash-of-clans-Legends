package model.units;

import model.world.Cell;

public abstract class Hero extends Unit {
	private final String name;
    private int specialActionCooldown;
    private final HeroType type;
    private int level;
    
    public Hero(String name, HeroType type, int maxHp, int maxActions, int range) {
        super(maxHp, maxActions, range);
        this.name = name;
        this.type = type;
        this.level = 1;
    }
    
	    public String getName() {
	        return name;
	    }
	
	    public int getSpecialActionCooldown() {
	        return specialActionCooldown;
	    }
	
	    public void setSpecialActionCooldown(int specialActionCooldown) {
	        this.specialActionCooldown = specialActionCooldown;
	    }
	
	    public HeroType getType() {
	        return type;
	    }
	
	    public int getLevel() {
	        return level;
	    }
	
	    public void setLevel(int level) {
	        if (level >= 1 && level <= 3) {
	            this.level = level;
	        }
	    }
	}
