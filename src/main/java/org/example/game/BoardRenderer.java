package org.example.game;

public class BoardRenderer {
    // Use a strategy to allow swapping different renderers (Strategy pattern).
    private static final BoardRendererStrategy RENDERER = new DefaultBoardRenderer();

    public static String renderBoard(String[][] board) {
        return RENDERER.render(board, RenderOptions.DEFAULT);
    }

    public static String renderBoard(String[][] board, RenderOptions options) {
        return RENDERER.render(board, options == null ? RenderOptions.DEFAULT : options);
    }
}
