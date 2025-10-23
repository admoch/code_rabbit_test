package org.example.game;

public class DefaultBoardRenderer implements BoardRendererStrategy {
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
