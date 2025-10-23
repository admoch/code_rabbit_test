package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void move() {
        Game game = new Game();
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