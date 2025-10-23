package org.example;

public enum Direction {
    RIGHT(0, 1), LEFT(0, -1), DOWN(-1, 0), UP(1, 0);
    private int x;
    private int y;

    /**
     * Constructs a Direction with the given coordinate offsets.
     *
     * @param x the x-component offset for this direction
     * @param y the y-component offset for this direction
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Provides the x component of this direction's coordinate.
     *
     * @return the x coordinate value associated with the direction
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y coordinate of this direction.
     *
     * @return the y coordinate for this Direction
     */
    public int getY() {
        return y;
    }
}