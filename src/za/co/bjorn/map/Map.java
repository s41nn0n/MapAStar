package za.co.bjorn.map;

import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<String>> map;


    public Map(ArrayList<ArrayList<String>> map) {
        this.map = map;
    }

    public Map() {
        this.map = new ArrayList<ArrayList<String>>();
    }

    public void setTerrain(int x, int y, String t) {
        map.get(y).set(x, t);
    }

    public void setWalked(int x, int y) {
        map.get(y).set(x, "#");
    }

    public String toString() {
        StringBuilder returnValue = new StringBuilder();
        for (ArrayList<String> x: map) {
            for (String point: x) {
                    returnValue.append(point);
            }
            returnValue.append("\n");
        }
        return returnValue.toString();
    }
}
