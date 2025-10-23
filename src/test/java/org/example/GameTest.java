package org.example;

import org.example.game.Direction;
import org.example.game.Game;
import org.junit.Test;
import org.junit.Ignore;

@Ignore("Disabled by default - enable when actively testing game moves")
public class GameTest {

    @Test
    public void move() {
        Game game = new Game(true);
        game.start();
        game.move();
        game.move();
        game.direction(Direction.UP);
        game.move();
        game.move();
        game.move();
        game.move();
        game.move();
        game.direction(Direction.RIGHT);
        game.move();
        game.move();
        game.move();
        game.move();
    }
}