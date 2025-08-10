package model.world;

import model.buildings.Building;

public class BuildingCell extends Cell {
   
	private final Building building;

    public BuildingCell(Building building) {
        super();
        this.building = building;
    }

    public Building getBuilding() {
        return building;
    }
}
