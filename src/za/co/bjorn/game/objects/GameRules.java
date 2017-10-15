package za.co.bjorn.game.objects;

import java.util.ArrayList;
import java.util.HashMap;

public class GameRules {
    private static final HashMap<Character, Boolean> validPathToChar = new HashMap<>();
    private static final HashMap<Character, Integer> gameNodeCost = new HashMap<>();
    private static final ArrayList<Character> gameMap = new ArrayList<Character>();

    public static final char startChar = '@';
    public static final char endChar = 'X';
    public static final char visitedChar = '#';

    public static final String smallMap = "./maps/small_map.txt";
    public static final String largeMap = "./maps/large_map.txt";

    public static final  int initialCapacity = 1000;

    static {
        gameNodeCost.put('*', 2);
        gameNodeCost.put('@', 1);
        gameNodeCost.put('.', 1);
        gameNodeCost.put('^', 3);

        validPathToChar.put('*', true);
        validPathToChar.put('~', false);
        validPathToChar.put('@', true);
        validPathToChar.put('.', true);
        validPathToChar.put('^', true);
        validPathToChar.put('X', true);

        gameMap.add('@');
        gameMap.add('.');
        gameMap.add('^');
        gameMap.add('X');
        gameMap.add('~');
        gameMap.add('*');
    }

    public static int getGameNodeCost(char value) {
        if (gameNodeCost.containsKey(value))
            return gameNodeCost.get(value);
        else return -1;
    }

    public static boolean getValidPathToChar(char value) {
        if (validPathToChar.containsKey(value))
            return validPathToChar.get(value);
        else return false;
    }


    public static boolean checkValidMapChar(char value) {
        return gameMap.contains(value);
    }

}
