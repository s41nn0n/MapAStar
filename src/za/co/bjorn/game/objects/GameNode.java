package za.co.bjorn.game.objects;

import java.util.ArrayList;
import java.util.Arrays;

public class GameNode {
    private ArrayList<GameNode> validPath;
    private int x;
    private int y;
    private int cost;
    private char type;
    private GameNode previous;


    public GameNode(int x, int y, char type) {
        validPath = new ArrayList<GameNode>();

        this.x = x;
        this.y = y;
        this.type = type;
        this.cost = GameRules.getGameNodeCost(type);

    }

    public void addPath(GameNode path) {
        if (GameRules.getValidPathToChar(path.getType())) {
            this.validPath.add(path);
        }
    }


    public ArrayList<GameNode> getValidPath() {
        return validPath;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCost() {
        return cost;
    }

    public void setValidPath(ArrayList<GameNode> validPath) {
        this.validPath = validPath;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public GameNode getPrevious() {
        return previous;
    }

    public void setPrevious(GameNode previous) {
        this.previous = previous;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

}
