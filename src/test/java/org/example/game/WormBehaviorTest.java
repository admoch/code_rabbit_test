package org.example.game;

import org.junit.Assert;
import org.junit.Test;

public class WormBehaviorTest {

    @Test
    public void segmentsFollowHead_and_growEveryThreeMoves() {
        Worm worm = new Worm(10);

        // initial
        Assert.assertEquals(1, worm.getBody().size());
        Assert.assertArrayEquals(new int[]{0,0}, worm.getHeadPosition());

        // Move 1 -> head to (0,1)
        worm.setDirection(Direction.RIGHT);
        worm.moveCells();
        Assert.assertArrayEquals(new int[]{0,1}, worm.getHeadPosition());
        Assert.assertEquals(1, worm.getBody().size());

        // Move 2 -> head to (0,2)
        worm.moveCells();
        Assert.assertArrayEquals(new int[]{0,2}, worm.getHeadPosition());
        Assert.assertEquals(1, worm.getBody().size());

        // Move 3 -> head to (0,3) and grow: tail should be at previous head position (0,2)
        worm.moveCells();
        Assert.assertArrayEquals(new int[]{0,3}, worm.getHeadPosition());
        Assert.assertEquals(2, worm.getBody().size());
        // head first, tail second
        Assert.assertEquals(0, worm.getBody().get(0).x());
        Assert.assertEquals(3, worm.getBody().get(0).y());
        Assert.assertEquals(0, worm.getBody().get(1).x());
        Assert.assertEquals(2, worm.getBody().get(1).y());

        // Move 4 -> head to (0,4), tail should move to previous head (0,3)
        worm.moveCells();
        Assert.assertArrayEquals(new int[]{0,4}, worm.getHeadPosition());
        Assert.assertEquals(2, worm.getBody().size());
        Assert.assertEquals(0, worm.getBody().get(0).x());
        Assert.assertEquals(4, worm.getBody().get(0).y());
        Assert.assertEquals(0, worm.getBody().get(1).x());
        Assert.assertEquals(3, worm.getBody().get(1).y());
    }
}

