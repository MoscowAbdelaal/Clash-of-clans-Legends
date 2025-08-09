package engine;
//test
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import model.units.*;
import model.world.Cell;

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
}
