package model.units;

import java.awt.Point;
import model.world.Cell;

public abstract class Unit {
	private Point location;
	private int maxHp;
	private int currentHp;
	private int maxActions;
	private int range;
	private int actionsAvailable;
	private Cell target;
	
		public Unit(int maxHp, int maxActions, int range) {
			this.maxHp = maxHp;
	    	this.currentHp = maxHp;
	        this.maxActions = maxActions;
	        this.actionsAvailable = maxActions;
	    	this.range = range;
		}

		public Point getLocation() {
			return location;
		}

		public void setLocation(Point location) {
			this.location = location;
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

		public int getMaxActions() {
			return maxActions;
		}

		public void setMaxActions(int maxActions) {
			this.maxActions = maxActions;
		}

		public int getRange() {
			return range;
		}

		public void setRange(int range) {
			this.range = range;
		}

		public int getActionsAvailable() {
			return actionsAvailable;
		}

		public void setActionsAvailable(int actionsAvailable) {
			this.actionsAvailable = actionsAvailable;
		}

		public Cell getTarget() {
			return target;
		}

		public void setTarget(Cell target) {
			this.target = target;
		}
	
}
