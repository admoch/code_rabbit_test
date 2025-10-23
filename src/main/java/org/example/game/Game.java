package org.example.game;

public class Game {
    public static final int BOARD_SIZE = 10;

    Worm worm;
    private String[][] lastBoard;

    // New: control whether move() prints the rendered board
    private final boolean verbose;
    // New: per-game render options
    private final RenderOptions renderOptions;

    /**
     * Creates a Game with verbose output disabled and the default render options.
     */
    public Game() {
        this(false, RenderOptions.DEFAULT);
    }

    /**
     * Construct a Game configured with the given verbosity and the default render options.
     *
     * @param verbose true to print the rendered board after each move, false to suppress printing
     */
    public Game(boolean verbose) {
        this(verbose, RenderOptions.DEFAULT);
    }

    /**
     * Creates a Game with verbose logging disabled and the specified render options.
     *
     * If `options` is null, the game's render options default to {@code RenderOptions.DEFAULT}.
     *
     * @param options render configuration to use for board rendering; may be null
     */
    public Game(RenderOptions options) {
        this(false, options);
    }

    /**
     * Create a Game configured with verbosity and render options.
     *
     * @param verbose whether the game should print the rendered board after each move
     * @param options the render options to use; if `null`, `RenderOptions.DEFAULT` will be used
     */
    public Game(boolean verbose, RenderOptions options) {
        this.verbose = verbose;
        this.renderOptions = options == null ? RenderOptions.DEFAULT : options;
    }

    /**
     * Initialize or replace the game's Worm using the configured board size.
     */
    public void start() {
        worm = new Worm(BOARD_SIZE);
    }

    /**
     * Advances the game's worm by one step, updates the cached board state, and optionally prints the rendered board.
     *
     * If the worm has not been created, this method initializes it with BOARD_SIZE before performing the move.
     */
    public void move() {
        if (worm == null) {
            worm = new Worm(BOARD_SIZE);
        }
        lastBoard = worm.moveCells();
        if (verbose) {
            System.out.print(renderBoard(lastBoard, renderOptions));
        }
    }

    /**
     * Sets the worm's movement direction, creating and starting a new worm if one does not yet exist.
     *
     * @param direction the direction to assign to the worm's next move
     */
    public void direction(Direction direction) {
        if (worm == null) {
            worm = new Worm(BOARD_SIZE);
        }
        worm.setDirection(direction);
    }

    // Centralized renderer: delegate to BoardRenderer (extracted)
    /**
     * Render the given board into its textual representation using the provided render options.
     *
     * @param board   a 2D array representing the board cells to render
     * @param options optional render configuration; if provided the first non-null element is used, otherwise RenderOptions.DEFAULT is used
     * @return        the rendered board as a String
     */
    public static String renderBoard(String[][] board, RenderOptions... options) {
        RenderOptions opts = (options != null && options.length > 0 && options[0] != null)
                ? options[0]
                : RenderOptions.DEFAULT;
        return BoardRenderer.renderBoard(board, opts);
    }

    /**
     * Get the most recently produced board state.
     *
     * @return the latest board as a 2D String array, or `null` if no move has been performed yet
     */
    public String[][] getBoard() {
        return lastBoard;
    }

    /**
     * Retrieve the worm's head coordinates.
     *
     * @return an int[] where index 0 is the row and index 1 is the column of the worm's head,
     *         or `{-1, -1}` if the worm is not initialized
     */
    int[] getHeadPosition() {
        if (worm == null) {
            return new int[] { -1, -1 };
        }
        return worm.getHeadPosition();
    }
}