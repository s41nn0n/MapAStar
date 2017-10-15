package za.co.bjorn.game.objects;

import java.util.ArrayList;

public class GameNode {

    private ArrayList<GameNode> validPath;
    private int x;
    private int y;

    private int moveCost;
    private boolean visited = false;

    //Heuristic Value
    private int manhattenCost = -1;
    private char type;
    private GameNode previous;

    public int getHeuristicCost(){
        return this.manhattenCost + GameRules.getGameNodeCost(this.getType());
    }

    public void calculateNodeHCost(GameNode end) {
        if (this.manhattenCost == -1)
            this.manhattenCost = Math.abs((this.getX() - end.getX()) + (this.getY() - end.getY()));
    }

    public GameNode(int x, int y, char type) {
        validPath = new ArrayList<GameNode>();

        this.x = x;
        this.y = y;
        this.type = type;
        this.moveCost = GameRules.getGameNodeCost(type);

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

    public GameNode getPrevious() {
        return previous;
    }

    public char getType() {
        return type;
    }

    public void setType(char type) {
        this.type = type;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void setParent(GameNode parentNode) {
        if (this.previous != null)
            this.previous.setVisited(false);
        this.previous = parentNode;
        if (parentNode != null)
            parentNode.setVisited(true);
    }

    public String toString(){
        return this.getY() + ":" + this.getX() + " h => " + this.getHeuristicCost() + " m => " + this.moveCost;
    }

    public boolean hasParent() {
        return (this.previous != null);
    }

}
