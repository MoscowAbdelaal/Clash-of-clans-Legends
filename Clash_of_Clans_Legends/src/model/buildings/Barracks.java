package model.buildings;

import exceptions.InvalidActionException;
import model.units.*;
import model.world.Upgradeable;

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

    public void upgrade() throws Exception {
        if (getLevel() >= 3) {
            throw new Exception("Barracks has reached the maximum level.");
        }
        setLevel(getLevel() + 1);
    }

    public SupportUnit recruit(String s) throws Exception {
        if (!canRecruit) {
            throw new InvalidActionException("Barracks already recruited this turn");
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

        unit.setLevel(this.getLevel());
        canRecruit = false;
        return unit;
    }
}
