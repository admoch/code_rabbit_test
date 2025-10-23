package org.example.game;

public class Game {
    public static final int BOARD_SIZE = 10;

    Worm worm;
    private String[][] lastBoard;

    // New: control whether move() prints the rendered board
    private final boolean verbose;
    // New: per-game render options
    private final RenderOptions renderOptions;

    public Game() {
        this(false, RenderOptions.DEFAULT);
    }

    public Game(boolean verbose) {
        this(verbose, RenderOptions.DEFAULT);
    }

    public Game(RenderOptions options) {
        this(false, options);
    }

    public Game(boolean verbose, RenderOptions options) {
        this.verbose = verbose;
        this.renderOptions = options == null ? RenderOptions.DEFAULT : options;
    }

    public void start() {
        worm = new Worm(BOARD_SIZE);
    }

    public void move() {
        if (worm == null) {
            worm = new Worm(BOARD_SIZE);
        }
        lastBoard = worm.moveCells();
        if (verbose) {
            System.out.print(renderBoard(lastBoard, renderOptions));
        }
    }

    public void direction(Direction direction) {
        if (worm == null) {
            worm = new Worm(BOARD_SIZE);
        }
        worm.setDirection(direction);
    }

    // Centralized renderer: delegate to BoardRenderer (extracted)
    // Use a single varargs method to avoid duplication while preserving existing usages
    public static String renderBoard(String[][] board, RenderOptions... options) {
        RenderOptions opts = (options != null && options.length > 0 && options[0] != null)
                ? options[0]
                : RenderOptions.DEFAULT;
        return BoardRenderer.renderBoard(board, opts);
    }

    // Return latest board (after move). May be null if no move has been performed yet.
    public String[][] getBoard() {
        return lastBoard;
    }

    // Package-visible accessor used by tests to inspect the worm head coordinates.
    int[] getHeadPosition() {
        if (worm == null) {
            return new int[] { -1, -1 };
        }
        return worm.getHeadPosition();
    }
}
