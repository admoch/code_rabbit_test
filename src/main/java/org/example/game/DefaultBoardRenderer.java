package org.example.game;

public class DefaultBoardRenderer implements BoardRendererStrategy {
    /**
     * Render a 2D board into a formatted string representation.
     *
     * The result preserves a configurable number of leading blank lines from {@code options.leadingBlankLines()},
     * uses the longest cell content (or {@code options.emptySymbol()} minimum) to determine per-cell width,
     * forces ANSI color sequences for cell content, and emits rows from bottom to top so the visual output
     * appears with the original top row at the top.
     *
     * @param board   a rectangular 2D array of cell contents; cells may be {@code null} to indicate emptiness.
     *                If {@code board} is {@code null} or has zero rows, the method returns only the leading blank lines.
     * @param options rendering options that supply values such as {@code leadingBlankLines()} and {@code emptySymbol()}.
     *                Note: the renderer always enables ANSI colors and ignores any {@code useAnsiColors()} setting.
     * @return        the formatted board as a string, including leading blank lines and a newline after each rendered row.
     */
    @Override
    public String render(String[][] board, RenderOptions options) {
        StringBuilder sb = new StringBuilder();
        // preserve configurable leading blank lines
        sb.append("\n".repeat(Math.max(0, options.leadingBlankLines())));

        if (board == null || board.length == 0) {
            return sb.toString();
        }

        int rows = board.length;
        int cols = board[0].length;

        int maxContentWidth = computeMaxContentWidth(board, options);
        int cellWidth = Math.max(1, maxContentWidth);

        // Always use ANSI colors regardless of options.useAnsiColors
        boolean colors = true;

        // Render only rows (top to bottom)
        for (int r = rows - 1; r >= 0; r--) {
            appendRow(sb, board, r, cols, cellWidth, colors, options);
        }

        return sb.toString();
    }

    /**
     * Compute the maximum width, in characters, required to render any cell of the board.
     *
     * @param board   a non-null 2D array of cell strings (rows x columns); null cells are ignored
     * @param options rendering options; {@link RenderOptions#emptySymbol()} provides a minimum width and may be null
     * @return the maximum length between the (possibly null) empty symbol's length (or 1 if it is null) and the lengths of all non-null cells
     */
    private static int computeMaxContentWidth(String[][] board, RenderOptions options) {
        int maxContentWidth = options.emptySymbol() != null ? options.emptySymbol().length() : 1;
        for (String[] row : board) {
            for (String cell : row) {
                if (cell != null) {
                    maxContentWidth = Math.max(maxContentWidth, cell.length());
                }
            }
        }
        return maxContentWidth;
    }

    /**
     * Append a single rendered row of the board to the provided StringBuilder.
     *
     * The row is rendered without borders or labels, with cells separated by a single space.
     * Each cell is padded on the right to reach {@code cellWidth}. If a cell is null the
     * renderer uses {@code options.emptySymbol()} (or the empty string if that is null).
     * When {@code colors} is true, non-null cells are wrapped in green ANSI codes and
     * null cells in dim ANSI codes. A newline is appended after the row.
     *
     * @param sb the StringBuilder to append the rendered row to
     * @param board the 2D board array containing cell contents (may contain nulls)
     * @param r the row index to render (0-based)
     * @param cols the number of columns to render from the row
     * @param cellWidth the width to which each cell's text is padded (minimum 1)
     * @param colors if true, apply ANSI coloring to cell contents (green for non-null, dim for null)
     * @param options render options providing {@code emptySymbol()} for null cells
     */
    private static void appendRow(StringBuilder sb, String[][] board, int r, int cols, int cellWidth, boolean colors, RenderOptions options) {
        // Simplified row rendering: no row labels, no cell borders. Cells separated by single spaces.
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_DIM = "\u001B[2m";

        for (int c = 0; c < cols; c++) {
            String raw = board[r][c] == null ? options.emptySymbol() : board[r][c];
            if (raw == null) raw = "";
            int padSize = cellWidth - raw.length();
            String spaces = " ".repeat(Math.max(0, padSize));

            if (colors) {
                String colored = board[r][c] == null ? (ANSI_DIM + raw + ANSI_RESET) : (ANSI_GREEN + raw + ANSI_RESET);
                sb.append(colored).append(spaces);
            } else {
                sb.append(raw).append(spaces);
            }

            if (c < cols - 1) sb.append(' ');
        }
        sb.append('\n');
    }
}