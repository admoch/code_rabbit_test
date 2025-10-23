package org.example.game;

public record RenderOptions(int leadingBlankLines, String emptySymbol) {
    // Default: keep a few leading blank lines and use default empty symbol
    public static final RenderOptions DEFAULT = new RenderOptions(4, "_");
}
