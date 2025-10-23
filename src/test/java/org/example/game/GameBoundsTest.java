package org.example.game;

import org.junit.Assert;
import org.junit.Test;

public class GameBoundsTest {

    @Test
    public void clampingBehavior() {
        Game game = new Game();
        game.start(); // ensure worm is initialized

        // Move left (negative y) several times - should clamp at 0
        game.direction(Direction.LEFT);
        for (int i = 0; i < 5; i++) {
            game.move();
        }
        int[] pos = game.getHeadPosition();
        Assert.assertEquals("x should remain >=0", 0, pos[0]);
        Assert.assertEquals("y should be clamped to 0 when moving left from 0", 0, pos[1]);

        // Move up (increasing x) beyond the board - should clamp at BOARD_SIZE-1 (9)
        game.direction(Direction.UP);
        for (int i = 0; i < 15; i++) {
            game.move();
        }
        pos = game.getHeadPosition();
        Assert.assertEquals("x should be clamped to BOARD_SIZE-1 (9)", 9, pos[0]);
        Assert.assertTrue("y should still be within bounds", pos[1] >= 0 && pos[1] < 10);

        // Move right (increasing y) beyond the board - should clamp at 9
        game.direction(Direction.RIGHT);
        for (int i = 0; i < 20; i++) {
            game.move();
        }
        pos = game.getHeadPosition();
        Assert.assertEquals("y should be clamped to BOARD_SIZE-1 (9)", 9, pos[1]);

        // Move down (decreasing x) beyond lower bound - should clamp to 0
        game.direction(Direction.DOWN);
        for (int i = 0; i < 20; i++) {
            game.move();
        }
        pos = game.getHeadPosition();
        Assert.assertEquals("x should be clamped back to 0 after moving down", 0, pos[0]);
    }
}

