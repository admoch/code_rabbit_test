package org.example;

import org.example.game.Direction;
import org.example.game.Game;
import org.junit.Test;

public class GameTest {

    @Test
    public void move() {
        Game game = new Game(true);
        game.start();
        game.move();
        game.direction(Direction.UP);
        game.move();
        game.move();
        game.move();
        game.direction(Direction.RIGHT);
        game.move();
        game.move();
        game.move();
    }
}