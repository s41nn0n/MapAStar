package za.co.bjorn;

import za.co.bjorn.game.Game;
import za.co.bjorn.game.objects.GameRules;
import za.co.bjorn.search.astar.AStar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = null;
        Game game = new Game();
        try {
            br = new BufferedReader(new FileReader(GameRules.largeMap));
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

                AStar aStar = new AStar();
                aStar.findPath(game);

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
