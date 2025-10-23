package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    Worm worm;

    /**
     * Initializes the game's worm by creating a new Worm instance and assigning it to the `worm` field, resetting any previous worm state.
     */
    public void start() {
        worm = new Worm();
    }

    /**
     * Advance the worm's position by one step, creating a new worm first if none exists.
     */
    public void move() {
        if(worm == null) {
            worm = new Worm();
        }
        worm.moveCells();
    }

    /**
     * Set the worm's movement direction.
     *
     * @param direction the new movement direction for the worm
     * @throws NullPointerException if the worm has not been initialized
     */
    public void direction(Direction direction) {
        worm.direction = direction;
    }

    private class Worm {
        private Direction direction = Direction.RIGHT;
        private ArrayList<Cells> body = new ArrayList<>();

        /**
         * Creates a new Worm with a single body segment located at coordinates (0, 0).
         */
        public Worm() {
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

        private class Cells {
            private int x;
            private int y;

            /**
             * Creates a new Cells instance at the specified coordinates.
             *
             * @param x the x-coordinate of the cell
             * @param y the y-coordinate of the cell
             */
            public Cells(int x, int y) {
                this.x = x;
                this.y = y;
            }

            /**
             * Gets the x coordinate of this cell.
             *
             * @return the x coordinate
             */
            public int getX() {
                return x;
            }

            /**
             * Sets the cell's x coordinate.
             *
             * @param x the new x coordinate
             */
            public void setX(int x) {
                this.x = x;
            }

            /**
             * Gets the y-coordinate of this cell.
             *
             * @return the y-coordinate of the cell
             */
            public int getY() {
                return y;
            }

            /**
             * Sets the y-coordinate of this cell.
             *
             * @param y the new y-coordinate
             */
            public void setY(int y) {
                this.y = y;
            }
        }

        /**
         * Advances each body segment by the worm's current direction, updates a 10x10 textual board
         * with the worm's segments marked as "X", and prints the board to standard output.
         *
         * <p>This method mutates the coordinates of the segments contained in {@code body} and
         * produces console output: four blank lines followed by the board where empty cells are
         * represented as "_" and occupied cells as "X". The board is indexed as [x][y] with a fixed
         * size of 10x10.
         */
        private void moveCells() {
            for (int i = body.size() - 1; i >= 0; i--) {
                moveCell(body.get(i), direction);
            }

            String[][] board = new String[10][10];
            for (Cells cells : body) {
                board[cells.getX()][cells.getY()] = "X";
            }

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();

            for (int i = board.length - 1; i >= 0; i--) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == null) {
                        board[i][j] = "_";
                    }
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }



        }

        /**
         * Advances the given body cell by adding the direction's per-axis deltas to its coordinates.
         *
         * @param cells the body cell whose coordinates will be updated
         * @param direction the movement direction providing X and Y deltas to apply
         */
        private void moveCell(Cells cells, Direction direction) {
            cells.setX(cells.getX() + direction.getX());
            cells.setY(cells.getY() + direction.getY());
        }

    }
}