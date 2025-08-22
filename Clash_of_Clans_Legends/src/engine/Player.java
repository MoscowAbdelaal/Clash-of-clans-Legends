package engine;

import exceptions.*;
import model.buildings.*;
import model.units.*;
import model.world.Upgradeable;

import java.util.ArrayList;

public class Player {
    private String name;
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

    public void useSpecial(Hero h, SupportUnit u) throws Exception {
        if (!units.contains(h))
            throw new InvalidActionException("This hero does not belong to the player.");

        if (h.getSpecialActionCooldown() > 0)
            throw new InvalidActionException("Hero's ability is on cooldown.");

        switch(h.getType()) {
        case PACIFIST:
            if (!units.contains(u))
                throw new InvalidActionException("Target must be one of this player's units.");
            break;

        case AGGRESSOR:
            if (units.contains(u))
                throw new InvalidActionException("Target must be an opponent's unit.");
            break;
        }

        h.useSpecial(u);
    }

    public Wall buildWall(Hero h) throws Exception {
        if (!units.contains(h))
            throw new InvalidActionException("Hero doesn't belong to this player.");
        if (goldAmount < 100)
            throw new NoAvailableResourcesException("Not enough gold.");
        if (h.getActionsAvailable() < 1)
            throw new NotEnoughActionsException("Hero has no actions left.");

        Wall wall = new Wall();
        buildings.add(wall);
        goldAmount -= 100;
        h.setActionsAvailable(h.getActionsAvailable() - 1);
        return wall;
    }

    public Tower buildTower(Hero h) throws Exception {
        if (!units.contains(h))
            throw new InvalidActionException("Hero doesn't belong to this player.");
        if (goldAmount < 100)
            throw new NoAvailableResourcesException("Not enough gold.");
        if (h.getActionsAvailable() < 1)
            throw new NotEnoughActionsException("Hero has no actions left.");

        Tower tower = new Tower();
        buildings.add(tower);
        goldAmount -= 100;
        h.setActionsAvailable(h.getActionsAvailable() - 1);
        return tower;
    }

    public SupportUnit recruit(Barracks b, String type) throws Exception {
        if (!buildings.contains(b))
            throw new InvalidActionException("This barracks does not belong to the player.");
        if (manpowerAmount < 100)
            throw new NoAvailableResourcesException("Not enough manpower.");

        SupportUnit unit = b.recruit(type);
        units.add(unit);
        manpowerAmount -= 100;
        return unit;
    }

    public void upgradeUnit(SupportUnit u) throws Exception {
        if (!units.contains(u))
            throw new InvalidActionException("This unit does not belong to the player.");
        int cost = 50 * (u.getLevel() + 1);
        if (manpowerAmount < cost)
            throw new NoAvailableResourcesException("Not enough manpower.");

        manpowerAmount -= cost;
        u.upgrade();
    }

    public void upgradeBuilding(Building b) throws Exception {
        if (!buildings.contains(b))
            throw new InvalidActionException("This building does not belong to the player.");
        int cost = 50 * (b.getLevel() + 1);
        if (goldAmount < cost)
            throw new NoAvailableResourcesException("Not enough gold");

        goldAmount -= cost;
        b.upgrade();
    }
}

