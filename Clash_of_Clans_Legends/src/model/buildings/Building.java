package model.buildings;

public abstract class Building {
	private int maxHp;
	private int currentHp;
	private int level;
	
		public Building(int maxHp) {
			this.maxHp = maxHp;
			currentHp = maxHp;
			level = 1;			
		}

		public int getMaxHp() {
			return maxHp;
		}

		public void setMaxHp(int maxHp) {
			this.maxHp = maxHp;
		}

		public int getCurrentHp() {
			return currentHp;
		}

		public void setCurrentHp(int currentHp) {
			this.currentHp = currentHp;
		}

		public int getLevel() {
			return level;
		}

		public void setLevel(int level) {
			this.level = level;
		}
		
}
