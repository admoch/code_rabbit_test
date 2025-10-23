package org.example.game;

import java.util.ArrayList;
import java.util.List;

public class Worm {
    private Direction direction = Direction.RIGHT;
    private final List<Cells> body = new ArrayList<>();
    private final int boardSize;
    // Counts how many times moveCells() has been called. When it reaches a multiple of growthInterval,
    // the worm grows by one cell (a new tail segment is appended at the previous tail position).
    private int moveCount = 0;
    // Number of moves between growth events. Default is 3 but can be changed at any time via setter.
    private int growthInterval = 3;

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
     * Creates a Worm and configures its growth interval at construction time.
     *
     * @param boardSize the board size
     * @param growthInterval positive integer number of moves between growths
     */
    public Worm(int boardSize, int growthInterval) {
        this(boardSize);
        setGrowthInterval(growthInterval);
    }

    /**
     * Configure how many moves it takes before the worm grows by one cell.
     * A value <= 0 is not allowed.
     *
     * This can be called at any time; it affects the next growth checks. It does not retroactively
     * change previously scheduled growth.
     *
     * @param interval positive integer number of moves between growths
     */
    public void setGrowthInterval(int interval) {
        if (interval <= 0) throw new IllegalArgumentException("growthInterval must be > 0");
        this.growthInterval = interval;
    }

    /**
     * Get the configured growth interval (defaults to 3).
     *
     * @return moves between growth events
     */
    public int getGrowthInterval() {
        return growthInterval;
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
     * Additionally, the worm grows by one cell every `growthInterval` moves: the previous tail position is appended as a new
     * tail segment on every Nth invocation of this method where N == growthInterval.
     *
     * @return a boardSize-by-boardSize String[][] where occupied cells contain "X" and all other entries are null
     */
    public String[][] moveCells() {
        // Snapshot current positions so segments can follow the previous segment's old position.
        List<Cells> previousPositions = new ArrayList<>(body);

        // Move head by one step in the current direction.
        Cells oldHead = previousPositions.get(0);
        Cells newHead = moveCell(oldHead, direction);
        body.set(0, newHead);

        // Move each following segment into the previous position of the segment ahead of it.
        for (int i = 1; i < body.size(); i++) {
            Cells pos = previousPositions.get(i - 1);
            body.set(i, pos);
        }

        // Increment move counter and grow once every `growthInterval` moves by appending the previous tail position
        moveCount++;
        if (moveCount % growthInterval == 0) {
            Cells prevTail = previousPositions.get(previousPositions.size() - 1);
            body.add(new Cells(prevTail.x(), prevTail.y()));
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
     * Defensive copy of the worm's body for inspections in tests.
     *
     * @return an unmodifiable copy of the worm's body (head first)
     */
    public List<Cells> getBody() {
        return List.copyOf(body);
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