package model.buildings;

public class Tower extends Building {
	
	private int range;
	private boolean canAttack;
	
		public Tower() {
			super (200);
			this.range = 3;
			this.canAttack = true;
	}

		public int getRange() {
			return range;
		}

		public void setRange(int range) {
			this.range = range;
		}

		public boolean isCanAttack() {
			return canAttack;
		}

		public void setCanAttack(boolean canAttack) {
			this.canAttack = canAttack;
		}
}
