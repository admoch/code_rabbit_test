package org.example.game;

import java.util.ArrayList;
import java.util.List;

public class Worm {
    private Direction direction = Direction.RIGHT;
    private final List<Cells> body = new ArrayList<>();
    private final int boardSize;

    /**
     * Creates a Worm on a square board and places its initial head at (0, 0).
     *
     * The worm's body is initialized with a single segment located at coordinates (0, 0).
     *
     * @param boardSize the size (width and height) of the square board
     */
    public Worm(int boardSize) {
        this.boardSize = boardSize;
        Cells cell = new Cells(0, 0);
        body.add(cell);
    }

    /**
     * Sets the worm's movement direction.
     *
     * @param direction the new movement direction for the worm
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Get the worm's head coordinates.
     *
     * @return an int[2] containing the head's x and y coordinates; {-1, -1} if the worm has no segments
     */
    public int[] getHeadPosition() {
        if (body.isEmpty()) return new int[] { -1, -1 };
        Cells head = body.get(0);
        return new int[] { head.x(), head.y() };
    }

    /**
     * Advance every worm segment by the current direction and produce a board marking their positions.
     *
     * The worm's body list is updated in place with moved segment positions. Cells that lie within the board
     * bounds are marked with the string "X"; positions outside the board are ignored.
     *
     * @return a boardSize-by-boardSize String[][] where occupied cells contain "X" and all other entries are null
     */
    public String[][] moveCells() {
        // Move each cell in the same direction (preserve original behavior), creating new Cells instances
        for (int i = body.size() - 1; i >= 0; i--) {
            Cells moved = moveCell(body.get(i), direction);
            body.set(i, moved);
        }

        String[][] board = new String[boardSize][boardSize];
        for (Cells c : body) {
            int x = c.x();
            int y = c.y();
            if (x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
                board[x][y] = "X";
            }
        }
        return board;
    }

    /**
     * Computes the next position by moving the given cell one step in the specified direction and constraining the result to the board bounds.
     *
     * @param cells     the current cell to move
     * @param direction the direction to move the cell
     * @return a new Cells instance at the resulting coordinates, clamped to the range [0, boardSize - 1]
     */
    private Cells moveCell(Cells cells, Direction direction) {
        int newX = cells.x() + direction.getX();
        int newY = cells.y() + direction.getY();

        if (newX < 0) newX = 0;
        if (newX >= boardSize) newX = boardSize - 1;
        if (newY < 0) newY = 0;
        if (newY >= boardSize) newY = boardSize - 1;

        return new Cells(newX, newY);
    }
}