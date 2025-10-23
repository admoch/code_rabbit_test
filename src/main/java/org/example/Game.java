package org.example;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    Worm worm;

    public void start() {
        worm = new Worm();
    }

    public void move() {
        if(worm == null) {
            worm = new Worm();
        }
        worm.moveCells();
    }

    public void direction(Direction direction) {
        worm.direction = direction;
    }

    private class Worm {
        private Direction direction = Direction.RIGHT;
        private ArrayList<Cells> body = new ArrayList<>();

        public Worm() {
            Cells cell = new Cells(0, 0);
            body.add(cell);
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        private class Cells {
            private int x;
            private int y;

            public Cells(int x, int y) {
                this.x = x;
                this.y = y;
            }

            public int getX() {
                return x;
            }

            public void setX(int x) {
                this.x = x;
            }

            public int getY() {
                return y;
            }

            public void setY(int y) {
                this.y = y;
            }
        }

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

        private void moveCell(Cells cells, Direction direction) {
            cells.setX(cells.getX() + direction.getX());
            cells.setY(cells.getY() + direction.getY());
        }

    }
}
