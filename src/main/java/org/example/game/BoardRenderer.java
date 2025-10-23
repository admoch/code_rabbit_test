package org.example.game;

public class BoardRenderer {
    // Use a strategy to allow swapping different renderers (Strategy pattern).
    private static final BoardRendererStrategy RENDERER = new DefaultBoardRenderer();

    /**
     * Render a 2D string board using the default render options.
     *
     * @param board a 2D array of strings where each sub-array represents a row of the board
     * @return the board rendered as a single String
     */
    public static String renderBoard(String[][] board) {
        return RENDERER.render(board, RenderOptions.DEFAULT);
    }

    /**
     * Render a 2D string board into its textual representation using the configured renderer.
     *
     * @param board   a 2D array where each element represents a cell's textual content; rows are the first dimension
     * @param options rendering options to control output formatting; if `null`, {@code RenderOptions.DEFAULT} is used
     * @return        the rendered board as a single string
     */
    public static String renderBoard(String[][] board, RenderOptions options) {
        return RENDERER.render(board, options == null ? RenderOptions.DEFAULT : options);
    }
}