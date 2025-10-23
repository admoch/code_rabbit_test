package org.example.game;

public interface BoardRendererStrategy {
    String render(String[][] board, RenderOptions options);
}

