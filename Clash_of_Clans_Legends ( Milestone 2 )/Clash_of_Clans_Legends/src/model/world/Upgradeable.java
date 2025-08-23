package model.world;

import exceptions.InvalidActionException;

public abstract interface Upgradeable {
	void upgrade() throws InvalidActionException;
}
