package model.buildings;


import model.units.*;
import model.world.Upgradeable;
import exceptions.InvalidActionException;

public class Barracks extends Building {
    private boolean canRecruit;

    public Barracks() {
        super(400);
        this.canRecruit = true;
    }

    public boolean isCanRecruit() {
        return canRecruit;
    }

    public void setCanRecruit(boolean canRecruit) {
        this.canRecruit = canRecruit;
    }

    public void upgrade() throws InvalidActionException {
        if (getLevel() >= 3) {
            throw new InvalidActionException("Support Units cannot be upgraded above level 3");
        }
        setLevel(getLevel() + 1);
    }

    public SupportUnit recruit(String s) throws InvalidActionException {
        if (!canRecruit) {
            throw new InvalidActionException("Barracks can only recruit once per turn, an InvalidActionException should be thrown.");
        }

        SupportUnit unit;
        switch (s.toLowerCase()) {
        case "footman":
            unit = new Footman();
            break;

        case "cavalry":
            unit = new Cavalry();
            break;

        case "archer":
            unit = new Archer();
            break;

        default:
            throw new InvalidActionException("Invalid unit type for recruitment: " + s);
        }

        for (int i = 1; i < this.getLevel(); i++) {
            unit.upgrade();
        }
        canRecruit = false;
        return unit;
    }
}
