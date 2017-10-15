package za.co.bjorn.game;

import za.co.bjorn.game.objects.GameMap;
import za.co.bjorn.game.objects.GameNode;
import za.co.bjorn.game.objects.GamePoint;
import za.co.bjorn.game.objects.GameRules;

import java.util.ArrayList;

public class Game {
    public Game() {
    }

    GamePoint currentPos, endPos;
    GameMap gameMap = new GameMap();
    int gameScore = 0;
    int maxY, maxX = -1;
    ArrayList<GameNode> gameNodes = new ArrayList<GameNode>();

    public int getGameScore() {
        return this.gameScore;
    }

    private void setGameScore(int gameScore) {
        this.gameScore = gameScore;
    }

    public ArrayList<GameNode> getGameNodes() {
        return this.gameNodes;
    }

    private void setGameNodes(ArrayList<GameNode> gameNodes) {
        this.gameNodes = gameNodes;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }


    public void setTerrain(int y, String s) {
        gameMap.setTerrain(y,s);
    }

    public void addNode(int x, int y, char type) {
        if (GameRules.startChar == type)
            currentPos = new GamePoint(x, y);
        if (GameRules.endChar == type)
            endPos = new GamePoint(x, y);

        gameNodes.add(new GameNode(x, y, type));
    }

    public void displayMap(){
        System.out.println(gameMap.toString());
    }

    public void buildPaths() {
        for (int yCounter = 0; yCounter < this.maxY; yCounter++) {
            for (int xCounter = 0; xCounter < this.maxY; xCounter++) {




                if(GameRules.getValidPathToChar(this.gameNodes.get((this.maxY*yCounter) + xCounter).getType())) {

                    buildPath(xCounter, yCounter);
                }
            }
        }

        for (int yCounter = 0; yCounter < this.maxY; yCounter++) {
            for (int xCounter = 0; xCounter < this.maxY; xCounter++) {
//                if(GameRules.getValidPathToChar(this.gameNodes.get((this.maxY*yCounter) + xCounter).getType())) {
//                    if (GameRules.startChar == this.gameNodes.get((this.maxY*yCounter) + xCounter).getType())
//                        start = new GamePoint(xCounter, yCounter);
//                    if (GameRules.endChar == this.gameNodes.get((this.maxY*yCounter) + xCounter).getType())
//                        end = new GamePoint(xCounter, yCounter);
//                    buildPath(xCounter, yCounter);
//                }

                System.out.println(yCounter + ":" + xCounter);
                for (GameNode g: this.gameNodes.get((this.maxY*yCounter) + xCounter).getValidPath()) {
                    System.out.println("\t" + g.getY() + ":" + g.getX() + " -=> " + g.getCost());
                }
            }
        }

    }

    private boolean checkBefore(int y){
        //This means we can build all paths with y axis before
        //This should always be null when y = 0
        StringBuffer yBeforeValue = this.gameMap.getY(y-1);
        return (yBeforeValue != null);
    }


    private boolean checkAfter(int y){
        //This means we can build all paths with y axis after
        //This should always be null when y = max(y)
        StringBuffer yAfterValue = this.gameMap.getY(y+1);
        return (yAfterValue != null);
    }

    private boolean checkLeft(int x){
        return (x > 0);
    }

    private boolean checkRight(int x){
        return (x < maxX-1);
    }

    private void buildPath(int x, int y) {



        this.gameNodes.get(this.maxY*y + x).calculateNodeHCost(this.gameNodes.get((this.maxY*(endPos.getY())) + endPos.getX()));
        boolean before = checkBefore(y);
        boolean after = checkAfter(y);
        boolean left = checkLeft(x);
        boolean right = checkRight(x);

//        if (x == 3 && y == 2) {
//            System.out.println(y+":"+x);
//            System.out.println("\tbefore = " + before);
//            System.out.println("\tafter = " + after);
//            System.out.println("\tleft = " + left);
//            System.out.println("\tright = " + left);
//            System.out.println("\t\t" + this.gameNodes.get((this.maxY*y) + x).getY() + ":" + this.gameNodes.get((this.maxY*y) + x).getX() + " => " + this.gameNodes.get((this.maxY*y) + x).getType());
//        }

        // Diagonals
        //Top Left
        if (before && left) {
            this.gameNodes.get((this.maxY*y) + x).addPath(this.gameNodes.get((this.maxY*(y-1)) + (x-1)));
        }
        //Top Right
        if (before && right) {
            this.gameNodes.get((this.maxY*y) + x).addPath(this.gameNodes.get((this.maxY*(y-1)) + (x+1)));
        }


        //Bottom Left
        if (after && left){
            this.gameNodes.get((this.maxY*y) + x).addPath(this.gameNodes.get((this.maxY*(y+1)) + (x-1)));
        }
        //Bottom Right
        if (after && right){
            this.gameNodes.get((this.maxY*y) + x).addPath(this.gameNodes.get((this.maxY*(y+1)) + (x+1)));
        }
        // Verticals
        if (before) {
            this.gameNodes.get((this.maxY*y) + x).addPath(this.gameNodes.get((this.maxY*(y-1)) + x));
        }

        if (after) {
            this.gameNodes.get((this.maxY*y) + x).addPath(this.gameNodes.get((this.maxY*(y+1)) + x));
        }

        // Horizontals
        // We need to do x + 1 and x - 1 as well
        if (left) {
            this.gameNodes.get(this.maxY*y + x).addPath(this.gameNodes.get(this.maxY*y + x-1));
        }
        if (right) {
            this.gameNodes.get(this.maxY*y + x).addPath(this.gameNodes.get(this.maxY*y + x+1));
        }

    }

    public void setMaxX(int x){
        if (this.maxX < x)
            this.maxX = x;
    }

    public void setMaxY(int y){
        if (this.maxY < y)
            this.maxY = y;
    }


    public GamePoint getStart() {
        return currentPos;
    }

    public void setStart(GamePoint start) {
        this.currentPos = start;
    }

    public GamePoint getEnd() {
        return endPos;
    }

    public void setEnd(GamePoint end) {
        this.endPos = end;
    }

    public boolean checkValidMap() {
        return true;
    }
}
