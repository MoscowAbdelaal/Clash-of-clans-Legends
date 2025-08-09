package model.buildings;

public class Barracks extends Building {
	private boolean canRecruit;
		
		public Barracks() {
			super (400);			
		}

		public boolean isCanRecruit() { //Change to canRecruit? 
			return canRecruit;
		}

		public void setCanRecruit(boolean canRecruit) {
			this.canRecruit = canRecruit;
		}
}
