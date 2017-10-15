package za.co.bjorn.game.objects;

import java.util.ArrayList;

public class GameMap {
    private ArrayList<StringBuffer> map;

    public GameMap() {
        this.map = new ArrayList<StringBuffer>();
    }

    public void setTerrain(int y, String terrain) {
//        System.out.println("setTerrain: " + y + " - "  + terrain);

        if (map.size() <= y) {
            map.add(y, new StringBuffer(terrain));
        } else {
//            StringBuffer r = new StringBuffer(terrain);
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
        map.get(y).setCharAt(x, '#');
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
