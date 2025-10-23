package org.example.game;

public interface BoardRendererStrategy {
    /**
 * Render the given 2D board into a textual representation using the provided options.
 *
 * @param board a two-dimensional array where board[row][col] is the cell value for that position
 * @param options configuration that controls visual aspects of rendering (for example delimiters, padding, orientation)
 * @return a String containing the rendered board layout
 */
String render(String[][] board, RenderOptions options);
}
