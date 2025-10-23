package org.example.game;

import org.junit.Assert;
import org.junit.Test;

public class GameNoCrashTest {

    @Test
    public void movingManyTimesDoesNotThrow_and_staysInBounds() {
        Game game = new Game();
        game.start();

        // Move up many times
        game.direction(Direction.UP);
        for (int i = 0; i < 1000; i++) {
            game.move();
            int[] pos = game.getHeadPosition();
            Assert.assertTrue("x in bounds", pos[0] >= 0 && pos[0] < Game.BOARD_SIZE);
            Assert.assertTrue("y in bounds", pos[1] >= 0 && pos[1] < Game.BOARD_SIZE);
        }

        // Move right many times
        game.direction(Direction.RIGHT);
        for (int i = 0; i < 1000; i++) {
            game.move();
            int[] pos = game.getHeadPosition();
            Assert.assertTrue("x in bounds", pos[0] >= 0 && pos[0] < Game.BOARD_SIZE);
            Assert.assertTrue("y in bounds", pos[1] >= 0 && pos[1] < Game.BOARD_SIZE);
        }

        // Move down many times
        game.direction(Direction.DOWN);
        for (int i = 0; i < 1000; i++) {
            game.move();
            int[] pos = game.getHeadPosition();
            Assert.assertTrue("x in bounds", pos[0] >= 0 && pos[0] < Game.BOARD_SIZE);
            Assert.assertTrue("y in bounds", pos[1] >= 0 && pos[1] < Game.BOARD_SIZE);
        }

        // Move left many times
        game.direction(Direction.LEFT);
        for (int i = 0; i < 1000; i++) {
            game.move();
            int[] pos = game.getHeadPosition();
            Assert.assertTrue("x in bounds", pos[0] >= 0 && pos[0] < Game.BOARD_SIZE);
            Assert.assertTrue("y in bounds", pos[1] >= 0 && pos[1] < Game.BOARD_SIZE);
        }
    }
}

