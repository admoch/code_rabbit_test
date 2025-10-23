package org.example;

public enum Direction {
    RIGHT(0, 1), LEFT(0, -1), DOWN(-1, 0), UP(1, 0);
    private int x;
    private int y;

    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
