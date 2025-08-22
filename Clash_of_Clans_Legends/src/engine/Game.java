package engine;

import exceptions.*;
import model.units.*;
import model.world.Cell;
import model.world.UnitCell;
import model.world.EmptyCell;
import model.world.ResourceCell;
import model.world.BuildingCell;
import model.world.ResourceType;
import model.buildings.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Point;

public class Game {
    public Player player1;
    public Player player2;
    public Player currentPlayer;
    public Cell[][] map;
    public static ArrayList<Hero> availableHeroes = new ArrayList<>();

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.map = new Cell[10][10];
    }

    public static void loadHeroes(String filePath) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");

            String name = parts[0].trim();
            String type = parts[1].trim();
            int maxHp = Integer.parseInt(parts[2].trim());
            int maxActions = Integer.parseInt(parts[3].trim());
            int range = Integer.parseInt(parts[4].trim());

            Hero hero = null;

            switch (type.toLowerCase()) {
                case "warchief":
                    int warchiefDmg = Integer.parseInt(parts[5].trim());
                    hero = new Warchief(name, maxHp, maxActions, range, warchiefDmg);
                    break;
                case "assassin":
                    int assassinDmg = Integer.parseInt(parts[5].trim());
                    hero = new Assassin(name, maxHp, maxActions, range, assassinDmg);
                    break;
                case "diplomat":
                    hero = new Diplomat(name, maxHp, maxActions, range);
                    break;
                case "monk":
                    hero = new Monk(name, maxHp, maxActions, range);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid hero type: " + type);
            }

            availableHeroes.add(hero);
        }

        br.close();
    }

    public void moveUp(int x, int y) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (!(map[x][y] instanceof UnitCell)) {
            throw new InvalidTargetException("No unit at specified position");
        }

        Unit unit = ((UnitCell) map[x][y]).getUnit();

        if (!currentPlayer.getUnits().contains(unit)) {
            throw new InvalidActionException("Unit does not belong to current player");
        }

        if (unit.getActionsAvailable() < 1) {
            throw new NotEnoughActionsException("Unit has no actions left");
        }

        int newY = y - 1;
        if (newY < 0) {
            throw new MovementException("Cannot move outside map boundaries");
        }

        if (!(map[x][newY] instanceof EmptyCell) && !(map[x][newY] instanceof ResourceCell)) {
            throw new MovementException("Cannot move to occupied cell");
        }

        map[x][newY] = new UnitCell(unit);
        map[x][y] = new EmptyCell();
        unit.setLocation(new Point(x, newY));
        unit.setActionsAvailable(unit.getActionsAvailable() - 1);
    }

    public void moveDown(int x, int y) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (!(map[x][y] instanceof UnitCell)) {
            throw new InvalidTargetException("No unit at specified position");
        }

        Unit unit = ((UnitCell) map[x][y]).getUnit();

        if (!currentPlayer.getUnits().contains(unit)) {
            throw new InvalidActionException("Unit does not belong to current player");
        }

        if (unit.getActionsAvailable() < 1) {
            throw new NotEnoughActionsException("Unit has no actions left");
        }

        int newY = y + 1;
        if (newY >= 10) {
            throw new MovementException("Cannot move outside map boundaries");
        }

        if (!(map[x][newY] instanceof EmptyCell) && !(map[x][newY] instanceof ResourceCell)) {
            throw new MovementException("Cannot move to occupied cell");
        }

        map[x][newY] = new UnitCell(unit);
        map[x][y] = new EmptyCell();
        unit.setLocation(new Point(x, newY));
        unit.setActionsAvailable(unit.getActionsAvailable() - 1);
    }

    public void moveLeft(int x, int y) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (!(map[x][y] instanceof UnitCell)) {
            throw new InvalidTargetException("No unit at specified position");
        }

        Unit unit = ((UnitCell) map[x][y]).getUnit();

        if (!currentPlayer.getUnits().contains(unit)) {
            throw new InvalidActionException("Unit does not belong to current player");
        }

        if (unit.getActionsAvailable() < 1) {
            throw new NotEnoughActionsException("Unit has no actions left");
        }

        int newX = x - 1;
        if (newX < 0) {
            throw new MovementException("Cannot move outside map boundaries");
        }

        if (!(map[newX][y] instanceof EmptyCell) && !(map[newX][y] instanceof ResourceCell)) {
            throw new MovementException("Cannot move to occupied cell");
        }

        map[newX][y] = new UnitCell(unit);
        map[x][y] = new EmptyCell();
        unit.setLocation(new Point(newX, y));
        unit.setActionsAvailable(unit.getActionsAvailable() - 1);
    }

    public void moveRight(int x, int y) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (!(map[x][y] instanceof UnitCell)) {
            throw new InvalidTargetException("No unit at specified position");
        }

        Unit unit = ((UnitCell) map[x][y]).getUnit();

        if (!currentPlayer.getUnits().contains(unit)) {
            throw new InvalidActionException("Unit does not belong to current player");
        }

        if (unit.getActionsAvailable() < 1) {
            throw new NotEnoughActionsException("Unit has no actions left");
        }

        int newX = x + 1;
        if (newX >= 10) {
            throw new MovementException("Cannot move outside map boundaries");
        }

        if (!(map[newX][y] instanceof EmptyCell) && !(map[newX][y] instanceof ResourceCell)) {
            throw new MovementException("Cannot move to occupied cell");
        }

        map[newX][y] = new UnitCell(unit);
        map[x][y] = new EmptyCell();
        unit.setLocation(new Point(newX, y));
        unit.setActionsAvailable(unit.getActionsAvailable() - 1);
    }

    public void build(int x, int y, int tx, int ty, String building) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10 || tx < 0 || tx >= 10 || ty < 0 || ty >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (!(map[x][y] instanceof UnitCell)) {
            throw new InvalidTargetException("No unit at specified position");
        }

        Unit unit = ((UnitCell) map[x][y]).getUnit();

        if (!(unit instanceof Hero)) {
            throw new InvalidActionException("Only heroes can build");
        }

        Hero hero = (Hero) unit;

        if (hero.getType() != HeroType.PACIFIST) {
            throw new InvalidActionException("Only pacifist heroes can build");
        }

        if (Math.abs(x - tx) + Math.abs(y - ty) != 1) {
            throw new InvalidTargetException("Can only build on adjacent cells");
        }

        if (!(map[tx][ty] instanceof EmptyCell)) {
            throw new InvalidTargetException("Can only build on empty cells");
        }

        Building newBuilding = null;

        if (building.equalsIgnoreCase("tower")) {
            newBuilding = currentPlayer.buildTower(hero);
        } else if (building.equalsIgnoreCase("wall")) {
            newBuilding = currentPlayer.buildWall(hero);
        } else {
            throw new InvalidActionException("Invalid building type");
        }

        map[tx][ty] = new BuildingCell(newBuilding);
    }

    public void recruitUnit(int x, int y, String unit) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (!(map[x][y] instanceof BuildingCell)) {
            throw new InvalidTargetException("No building at specified position");
        }

        Building building = ((BuildingCell) map[x][y]).getBuilding();

        if (!(building instanceof Barracks)) {
            throw new InvalidActionException("Only barracks can recruit units");
        }

        Barracks barracks = (Barracks) building;

        if (!currentPlayer.getBuildings().contains(barracks)) {
            throw new InvalidActionException("Barracks does not belong to current player");
        }

        int spawnX = x + 1;
        int spawnY = y;

        if (spawnX >= 10) {
            throw new MovementException("No space to spawn unit");
        }

        if (!(map[spawnX][spawnY] instanceof EmptyCell)) {
            throw new MovementException("Spawn position is occupied");
        }

        SupportUnit newUnit = currentPlayer.recruit(barracks, unit);

        map[spawnX][spawnY] = new UnitCell(newUnit);
        newUnit.setLocation(new Point(spawnX, spawnY));
    }

    public void upgrade(int x, int y) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (map[x][y] instanceof UnitCell) {
            Unit unit = ((UnitCell) map[x][y]).getUnit();

            if (!(unit instanceof SupportUnit)) {
                throw new InvalidActionException("Only support units can be upgraded");
            }

            SupportUnit supportUnit = (SupportUnit) unit;

            if (!currentPlayer.getUnits().contains(supportUnit)) {
                throw new InvalidActionException("Unit does not belong to current player");
            }

            currentPlayer.upgradeUnit(supportUnit);

        } else if (map[x][y] instanceof BuildingCell) {
            Building building = ((BuildingCell) map[x][y]).getBuilding();

            if (!currentPlayer.getBuildings().contains(building)) {
                throw new InvalidActionException("Building does not belong to current player");
            }

            currentPlayer.upgradeBuilding(building);

        } else {
            throw new InvalidTargetException("No upgradeable object at specified position");
        }
    }

    public void useSpecial(int x, int y, int tx, int ty) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10 || tx < 0 || tx >= 10 || ty < 0 || ty >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (!(map[x][y] instanceof UnitCell)) {
            throw new InvalidTargetException("No unit at specified position");
        }

        Unit unit = ((UnitCell) map[x][y]).getUnit();

        if (!(unit instanceof Hero)) {
            throw new InvalidActionException("Only heroes can use special actions");
        }

        Hero hero = (Hero) unit;

        if (!(map[tx][ty] instanceof UnitCell)) {
            throw new InvalidTargetException("No unit at target position");
        }

        Unit targetUnit = ((UnitCell) map[tx][ty]).getUnit();

        if (!(targetUnit instanceof SupportUnit)) {
            throw new InvalidActionException("Can only use special on support units");
        }

        SupportUnit target = (SupportUnit) targetUnit;

        int distance = Math.abs(x - tx) + Math.abs(y - ty);
        if (distance > hero.getRange()) {
            throw new InvalidTargetException("Target is out of range");
        }

        if (hero instanceof Diplomat) {
            Player enemyPlayer = (currentPlayer == player1) ? player2 : player1;

            if (!enemyPlayer.getUnits().contains(target)) {
                throw new InvalidActionException("Diplomat can only convert enemy units");
            }

            enemyPlayer.getUnits().remove(target);
            currentPlayer.getUnits().add(target);

        } else if (hero instanceof Assassin) {
            Player targetPlayer = (currentPlayer == player1) ? player2 : player1;

            if (!targetPlayer.getUnits().contains(target)) {
                throw new InvalidActionException("Assassin can only target enemy units");
            }

            targetPlayer.getUnits().remove(target);
            map[tx][ty] = new EmptyCell();

        } else {
            currentPlayer.useSpecial(hero, target);
        }
    }

    public void attack(int x, int y, int tx, int ty) throws Exception {
        if (x < 0 || x >= 10 || y < 0 || y >= 10 || tx < 0 || tx >= 10 || ty < 0 || ty >= 10) {
            throw new InvalidTargetException("Position out of bounds");
        }

        if (!(map[x][y] instanceof UnitCell) && !(map[x][y] instanceof BuildingCell)) {
            throw new InvalidTargetException("No attacker at specified position");
        }

        Unit attacker = null;
        Building buildingAttacker = null;

        if (map[x][y] instanceof UnitCell) {
            attacker = ((UnitCell) map[x][y]).getUnit();

            if (!currentPlayer.getUnits().contains(attacker)) {
                throw new InvalidActionException("Attacker does not belong to current player");
            }

            if (attacker.getActionsAvailable() < 1) {
                throw new NotEnoughActionsException("Attacker has no actions left");
            }
        } else {
            buildingAttacker = ((BuildingCell) map[x][y]).getBuilding();

            if (!currentPlayer.getBuildings().contains(buildingAttacker)) {
                throw new InvalidActionException("Attacker building does not belong to current player");
            }
        }

        if (!(map[tx][ty] instanceof UnitCell) && !(map[tx][ty] instanceof BuildingCell)) {
            throw new InvalidTargetException("No target at specified position");
        }

        Unit targetUnit = null;
        Building targetBuilding = null;

        if (map[tx][ty] instanceof UnitCell) {
            targetUnit = ((UnitCell) map[tx][ty]).getUnit();
        } else {
            targetBuilding = ((BuildingCell) map[tx][ty]).getBuilding();
        }

        Player enemyPlayer = (currentPlayer == player1) ? player2 : player1;

        if (targetUnit != null && !enemyPlayer.getUnits().contains(targetUnit)) {
            throw new InvalidTargetException("Can only attack enemy units");
        }

        if (targetBuilding != null && !enemyPlayer.getBuildings().contains(targetBuilding)) {
            throw new InvalidTargetException("Can only attack enemy buildings");
        }

        int distance = Math.abs(x - tx) + Math.abs(y - ty);
        int range = (attacker != null) ? attacker.getRange() : buildingAttacker.getRange();

        if (distance > range) {
            throw new InvalidTargetException("Target is out of range");
        }

        int damage = (attacker != null) ? attacker.getAttackDmg() : buildingAttacker.getAttackDmg();

        if (targetUnit != null) {
            targetUnit.setCurrentHp(targetUnit.getCurrentHp() - damage);

            if (targetUnit.getCurrentHp() <= 0) {
                enemyPlayer.getUnits().remove(targetUnit);
                map[tx][ty] = new EmptyCell();
            }
        } else {
            targetBuilding.setCurrentHp(targetBuilding.getCurrentHp() - damage);

            if (targetBuilding.getCurrentHp() <= 0) {
                enemyPlayer.getBuildings().remove(targetBuilding);
                map[tx][ty] = new EmptyCell();
            }
        }

        if (attacker != null) {
            attacker.setActionsAvailable(attacker.getActionsAvailable() - 1);
        }
    }

    public void endTurn() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;

        for (Unit unit : currentPlayer.getUnits()) {
            unit.setActionsAvailable(unit.getMaxActions());
        }

        for (Unit unit : currentPlayer.getUnits()) {
            if (unit instanceof Hero) {
                Hero hero = (Hero) unit;
                if (hero.getSpecialActionCooldown() > 0) {
                    hero.setSpecialActionCooldown(hero.getSpecialActionCooldown() - 1);
                }
            }
        }

        for (Building building : currentPlayer.getBuildings()) {
            if (building instanceof Tower) {
                ((Tower) building).setCanAttack(true);
            } else if (building instanceof Barracks) {
                ((Barracks) building).setCanRecruit(true);
            }
        }
    }

    public Player checkGameOver() {
        boolean player1PalaceExists = false;
        for (Building building : player1.getBuildings()) {
            if (building instanceof Palace) {
                player1PalaceExists = true;
                break;
            }
        }

        if (!player1PalaceExists) {
            return player2;
        }

        boolean player2PalaceExists = false;
        for (Building building : player2.getBuildings()) {
            if (building instanceof Palace) {
                player2PalaceExists = true;
                break;
            }
        }

        if (!player2PalaceExists) {
            return player1;
        }

        return null;
    }

    public void setMap(Hero h1, Hero h2) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = new EmptyCell();
            }
        }

        player1.getUnits().add(h1);
        player2.getUnits().add(h2);

        h1.setLocation(new Point(1, 0));
        h2.setLocation(new Point(8, 9));
        map[1][0] = new UnitCell(h1);
        map[8][9] = new UnitCell(h2);

        SupportUnit footman1 = new Footman();
        SupportUnit cavalry1 = new Cavalry();
        SupportUnit archer1 = new Archer();

        SupportUnit footman2 = new Footman();
        SupportUnit cavalry2 = new Cavalry();
        SupportUnit archer2 = new Archer();

        player1.getUnits().addAll(java.util.Arrays.asList(footman1, cavalry1, archer1));
        player2.getUnits().addAll(java.util.Arrays.asList(footman2, cavalry2, archer2));

        cavalry1.setLocation(new Point(1, 1));
        footman1.setLocation(new Point(1, 2));
        archer1.setLocation(new Point(1, 3));

        archer2.setLocation(new Point(8, 6));
        footman2.setLocation(new Point(8, 7));
        cavalry2.setLocation(new Point(8, 8));

        map[1][1] = new UnitCell(cavalry1);
        map[1][2] = new UnitCell(footman1);
        map[1][3] = new UnitCell(archer1);

        map[8][6] = new UnitCell(archer2);
        map[8][7] = new UnitCell(footman2);
        map[8][8] = new UnitCell(cavalry2);

        Palace palace1 = new Palace();
        Tower tower1 = new Tower();
        Barracks barracks1 = new Barracks();

        Palace palace2 = new Palace();
        Tower tower2 = new Tower();
        Barracks barracks2 = new Barracks();

        player1.getBuildings().addAll(java.util.Arrays.asList(palace1, tower1, barracks1));
        player2.getBuildings().addAll(java.util.Arrays.asList(palace2, tower2, barracks2));

        map[0][0] = new BuildingCell(palace1);
        map[0][1] = new BuildingCell(barracks1);
        map[0][3] = new BuildingCell(tower1);

        map[9][6] = new BuildingCell(tower2);
        map[9][7] = new BuildingCell(barracks2);
        map[9][9] = new BuildingCell(palace2);

        Random random = new Random();
        int goldCells = 0;
        int manpowerCells = 0;

        while (goldCells < 5 || manpowerCells < 5) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);

            if (map[x][y] instanceof EmptyCell) {
                if (goldCells < 5) {
                    map[x][y] = new ResourceCell(ResourceType.GOLD, 200);
                    goldCells++;
                } else if (manpowerCells < 5) {
                    map[x][y] = new ResourceCell(ResourceType.MANPOWER, 200);
                    manpowerCells++;
                }
            }
        }
    }
}
