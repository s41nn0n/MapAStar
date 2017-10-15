package za.co.bjorn;

import za.co.bjorn.game.objects.Game;
import za.co.bjorn.game.objects.GameMap;
import za.co.bjorn.game.objects.GameNode;
import za.co.bjorn.game.objects.GameRules;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = null;

        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));

        Game game = new Game();

        try {
            br = new BufferedReader(new FileReader("./maps/small_map.txt"));
            String line;
            int yCount = 0;
            while ((line = br.readLine()) != null) {
//                System.out.println("line: " + line);
                //Setting the Game map, for Humans
                game.setTerrain(yCount,line);
                int xCount = 0;
                // This builds up the Game Nodes
                for (char type: line.toCharArray()) {
                    game.addNode(xCount++, yCount, type);
                    game.setMaxX(xCount);
                }
                game.setMaxY(++yCount);
            }

            if (game.checkValidMap()) {
                game.buildPaths();
                game.displayMap();
            } else {
                //This should throw error
                System.out.println("The map was invalid");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
