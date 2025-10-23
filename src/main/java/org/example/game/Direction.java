package org.example.game;

public enum Direction {
    RIGHT(0, 1), LEFT(0, -1), DOWN(-1, 0), UP(1, 0);
    private final int x;
    private final int y;

    /**
     * Creates a Direction with the specified x and y components.
     *
     * @param x the x component of the direction vector
     * @param y the y component of the direction vector
     */
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