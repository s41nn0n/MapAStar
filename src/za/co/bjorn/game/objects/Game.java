package za.co.bjorn.game.objects;

import java.util.ArrayList;

public class Game {
    public Game() {
    }



    GamePoint start;
    GamePoint end;


    GameMap gameMap = new GameMap();
    int gameScore = 0;

    //This is essentially n - 1 columns / rows
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

    public void addNode(int x, int y, char s) {
        gameNodes.add(new GameNode(x, y, s));
    }

    public void displayMap(){
        System.out.println(gameMap.toString());
    }

    public void buildPaths() {
        for (int yCounter = 0; yCounter < this.maxY; yCounter++) {
            for (int xCounter = 0; xCounter < this.maxY; xCounter++) {
                if(GameRules.getValidPathToChar(this.gameNodes.get((this.maxY*yCounter) + xCounter).getType())) {
                    if (GameRules.startChar == this.gameNodes.get((this.maxY*yCounter) + xCounter).getType())
                        start = new GamePoint(xCounter, yCounter);
                    if (GameRules.endChar == this.gameNodes.get((this.maxY*yCounter) + xCounter).getType())
                        end = new GamePoint(xCounter, yCounter);

                    buildPath(xCounter, yCounter);
                }
            }
        }
    }

    private void buildPath(int x, int y) {
        StringBuffer yBeforeValue = this.gameMap.getY(y-1);
        StringBuffer yAfterValue = this.gameMap.getY(y+1);
//        StringBuffer yValue = gameMap.getY(y);
//        System.out.println("Working on: " + y + ":" + x);
//        System.out.println("Working on: " + this.gameNodes.get((this.maxY*(y)) + x).getY() + ":" + this.gameNodes.get((this.maxY*(y)) + x).getX());

        if (yBeforeValue != null) {
            //This means we can build all paths with y axis before
            //This should always be null when y = 0
//            System.out.println("\t1 addPath: " + this.gameNodes.get((this.maxY*(y-1)) + x).getY() + ":" + this.gameNodes.get((this.maxY*(y-1)) + x).getX());

            this.gameNodes.get((this.maxY*y) + x).addPath(this.gameNodes.get((this.maxY*(y-1)) + x));
        }

        if (yAfterValue != null) {
            //This means we can build all paths with y axis after
            //This should always be null when y = max(y)
//            System.out.println("\t2 addPath: " + this.gameNodes.get((this.maxY*(y+1)) + x).getY() + ":" + this.gameNodes.get((this.maxY*(y+1)) + x).getX());
            this.gameNodes.get((this.maxY*y) + x).addPath(this.gameNodes.get((this.maxY*(y+1)) + x));
        }

        //We need to do x + 1 and x - 1 as well
        if (x > 0) {
//            System.out.println("\t3 addPath: " + this.gameNodes.get((this.maxY*y) + x -1).getY() + ":" + this.gameNodes.get((this.maxY*y) + x -1).getX());
            this.gameNodes.get(this.maxY*y + x).addPath(this.gameNodes.get(this.maxY*y + x-1));
        }
        if (x < maxX-1) {
//            System.out.println("\t4 addPath: " + this.gameNodes.get((this.maxY*y) + x+1).getY() + ":" + this.gameNodes.get((this.maxY*y) + x+1).getX());
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
        return start;
    }

    public void setStart(GamePoint start) {
        this.start = start;
    }

    public GamePoint getEnd() {
        return end;
    }

    public void setEnd(GamePoint end) {
        this.end = end;
    }

    public boolean checkValidMap() {
        return true;
    }
}
