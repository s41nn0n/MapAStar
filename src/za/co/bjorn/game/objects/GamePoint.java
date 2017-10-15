package za.co.bjorn.game.objects;

public class GamePoint {
    public GamePoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {

        return x;
    }

    public int getY() {
        return y;
    }

    private int x, y = 0;
}
