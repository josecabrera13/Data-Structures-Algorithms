package com.jose.datastructuresalgorithms.week4.puzzle;

import org.junit.Assert;
import org.junit.Test;

public class SolverTest {

    @Test
    public void puzzleSolver() {
        int[][] puzzle = {{0, 1, 3}, {4, 2, 5}, {7, 8, 6}};
        int[][] puzzleExpected = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};

        Iterable<Board> solution = new Solver(new Board(puzzle)).solution();
        Assert.assertEquals(new Board(puzzleExpected), solution.iterator().next());
    }
}
