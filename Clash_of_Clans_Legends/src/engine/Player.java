package engine;

import model.units.Unit;
import model.buildings.Building;

import java.util.ArrayList;

public class Player {
    private final String name;
    private ArrayList<Unit> units;
    private ArrayList<Building> buildings;
    private int goldAmount;
    private int manpowerAmount;

    public Player(String name) {
        this.name = name;
        this.goldAmount = 700;
        this.manpowerAmount = 700;
        this.units = new ArrayList<>();
        this.buildings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getGoldAmount() {
        return goldAmount;
    }

    public void setGoldAmount(int goldAmount) {
        this.goldAmount = goldAmount;
    }

    public int getManpowerAmount() {
        return manpowerAmount;
    }

    public void setManpowerAmount(int manpowerAmount) {
        this.manpowerAmount = manpowerAmount;
    }
}
