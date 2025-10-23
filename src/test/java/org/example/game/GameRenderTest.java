package org.example.game;

import org.junit.Assert;
import org.junit.Test;

public class GameRenderTest {

    @Test
    public void moveUpdatesBoard_and_renderMatches() {
        Game game = new Game();
        game.start();

        // perform one move (default direction is RIGHT)
        game.move();

        // expected board: head moved from (0,0) to (0,1)
        String[][] expectedBoard = new String[Game.BOARD_SIZE][Game.BOARD_SIZE];
        expectedBoard[0][1] = "X";

        String expected = Game.renderBoard(expectedBoard);

        String[][] actualBoard = game.getBoard();
        String actual = Game.renderBoard(actualBoard);

        Assert.assertEquals("Rendered board after move should equal renderBoard result", expected, actual);
    }
}
