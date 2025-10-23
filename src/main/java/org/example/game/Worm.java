package org.example.game;

import java.util.ArrayList;
import java.util.List;

public class Worm {
    private Direction direction = Direction.RIGHT;
    private final List<Cells> body = new ArrayList<>();
    private final int boardSize;

    public Worm(int boardSize) {
        this.boardSize = boardSize;
        Cells cell = new Cells(0, 0);
        body.add(cell);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int[] getHeadPosition() {
        if (body.isEmpty()) return new int[] { -1, -1 };
        Cells head = body.get(0);
        return new int[] { head.x(), head.y() };
    }

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
