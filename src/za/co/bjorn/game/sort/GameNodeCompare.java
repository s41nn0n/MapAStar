package za.co.bjorn.game.sort;

import za.co.bjorn.game.objects.GameNode;

import java.util.Comparator;

public class GameNodeCompare implements Comparator<GameNode> {

    public int compare(GameNode gameNode1, GameNode gameNode2) {
//        System.out.println("=================");
//        System.out.println("Order => " + gameNode1.toString());
//        System.out.println("Order => " + gameNode2.toString());
//        System.out.println("=================");
        if (gameNode1.getHeuristicCost() < gameNode2.getHeuristicCost()) {
            return -1;
        } else if (gameNode1.getHeuristicCost() > gameNode2.getHeuristicCost()) {
            return 1;
        } else {
            return 1;
        }
    }
}
