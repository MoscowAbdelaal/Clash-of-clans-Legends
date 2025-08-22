package model.world;

import model.units.Unit;

public class UnitCell extends Cell {
    
	private Unit unit;

    public UnitCell(Unit unit) {
        super();
        this.unit = unit;
    }

    public Unit getUnit() {
        return unit;
    }
}
