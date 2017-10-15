package za.co.bjorn.map;

import java.util.ArrayList;

public class Terrain {
    private ArrayList<Terrain> validPath;
    private int x;
    private int y;
    private int cost;


    public Terrain(int x, int y, char type) {
        this.x = x;
        this.y = y;

        switch (type) {
            case '*': this.cost = 0;
                break;
            case '@': this.cost = 0;
                break;
            case 'X': this.cost = 0;
                break;
            case '^': this.cost = 0;
                break;
            default : this.cost = 0;
                break;
        }

    }


    public ArrayList<Terrain> getValidPath() {
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

    public void setValidPath(ArrayList<Terrain> validPath) {
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
}
