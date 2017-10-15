package za.co.bjorn.search.astar;

import za.co.bjorn.game.Game;
import za.co.bjorn.game.objects.GameNode;
import za.co.bjorn.game.objects.GamePoint;
import za.co.bjorn.game.objects.GameRules;
import za.co.bjorn.game.sort.GameNodeCompare;

import java.math.BigDecimal;
import java.util.*;

public class AStar {

    // The set of nodes already evaluated
    private ArrayList<GameNode> closedList;

    // The set of currently discovered nodes that are not evaluated yet.
    // Initially, only the start node is known.
    private PriorityQueue<GameNode> openList;
    private HashMap<GameNode, Integer> fScore;
    private HashMap<GameNode, Integer> gScore;
    private HashMap<GameNode, GameNode> cameFrom;
//    // For each node, which node it can most efficiently be reached from.
//    // If a node can be reached from many nodes, cameFrom will eventually contain the
//    // most efficient previous step.
//    //This is stored in teh GameNode
//    private HashMap<GameNode, Integer> gVals;
//    private HashMap<GameNode, Integer> fVals;


    public AStar() {
        openList = new PriorityQueue<GameNode>(GameRules.initialCapacity, new GameNodeCompare());
        closedList = new ArrayList<GameNode>();
        gScore = new HashMap<GameNode, Integer>();
        fScore = new HashMap<GameNode, Integer>();

    }

    public void findPath(Game game){
        closedList.clear();

        openList.clear();
        openList.add(game.getGameNodes().get((game.getStart().getY() * game.getMaxY()) + game.getStart().getX()));

        cameFrom = new HashMap<GameNode, GameNode>();

        gScore.put(game.getGameNodes().get((game.getStart().getY() * game.getMaxY()) + game.getStart().getX()), 0);

        fScore.put(game.getGameNodes().get((game.getStart().getY() * game.getMaxY()) + game.getStart().getX()),
                game.getGameNodes().get((game.getStart().getY() * game.getMaxY()) + game.getStart().getX()).getHeuristicCost());

        GameNode previous = null;
        while (!openList.isEmpty()) {
            GameNode current = openList.element();

            if (current.getValidPath().contains(previous))
                current.setParent(previous);

            if (current.equals(game.getGameNodes().get((game.getEnd().getY() * game.getMaxY()) + game.getEnd().getX()))) {
                System.out.println("DONE");
                ArrayList<GameNode> totalPath = reconstructPath(cameFrom, current, game);
                return;
            }

            openList.poll();
            closedList.add(current);

            for (GameNode neighbor: current.getValidPath()) {

                if (closedList.contains(neighbor))
                    continue;

                if (!openList.contains(neighbor))
                    openList.add(neighbor);

                if (gScore.get(neighbor) == null)
                    gScore.put(neighbor, GameRules.initialCapacity);

                int tentativeGScore = gScore.get(current) + neighbor.getHeuristicCost();
                if (tentativeGScore >= gScore.get(neighbor))
                    continue;


                cameFrom.put(neighbor, current);
                gScore.put(neighbor, tentativeGScore);
                fScore.put(neighbor, gScore.get(neighbor) + neighbor.getHeuristicCost());

                if (neighbor.getY() == 49 && neighbor.getX() == 48) {
//                    System.out.println("neighbor.getY() => " + neighbor.getY());
                    if (cameFrom.get(neighbor) != null) {
                        System.out.println("---" + cameFrom.get(neighbor).toString());
                    }
                }
            }


//            if ((previous == null) || ((current.getPrevious() != null) && (current.getPrevious().getCost() <= previous.getCost())))
//                previous = current;

//            if (current.getPrevious() != null) {
//                System.out.println("current.getPrevious().getCost() => " + current.getPrevious().getCost());
//                System.out.println("current.getCost() => " + current.getCost());
//            }
        }
//        reader.close();

        System.out.println("There was no valid path");
    }


    private ArrayList<GameNode> reconstructPath(HashMap<GameNode, GameNode> cameFrom, GameNode current, Game game) {
        ArrayList<GameNode> totalPath = new ArrayList<GameNode>();
        totalPath.add(current);
        game.setVisited(current.getX(), current.getY());
        while (cameFrom.get(current) != null) {
            current = cameFrom.get(current);
            System.out.println(current.toString());
            totalPath.add(current);
            game.setVisited(current.getX(), current.getY());
        }
        return totalPath;
    }

}
