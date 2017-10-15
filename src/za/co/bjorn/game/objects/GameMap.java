package za.co.bjorn.game.objects;

import java.util.ArrayList;

public class GameMap {
    private ArrayList<StringBuffer> map;

    public GameMap() {
        this.map = new ArrayList<StringBuffer>();
    }

    public void setTerrain(int y, String terrain) {
        if (map.size() <= y) {
            map.add(y, new StringBuffer(terrain));
        } else {
            map.get(y).append(terrain);
        }
    }

    public StringBuffer getY(int y){
        if (y < 0) return null;
        if (y > map.size()-1) return null;
        else if (map.get(y) == null)
            return null;
        else return map.get(y);
    }


    public void setWalked(int x, int y) {
        map.get(y).setCharAt(x, GameRules.visitedChar);
    }

    public String toString() {
        StringBuilder returnValue = new StringBuilder();
        for (StringBuffer x: map) {
            returnValue.append(x.toString());
            returnValue.append("\n");
        }
        return returnValue.toString();
    }
}
