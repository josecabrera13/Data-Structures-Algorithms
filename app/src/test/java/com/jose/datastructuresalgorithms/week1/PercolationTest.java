package com.jose.datastructuresalgorithms.week1;

import com.jose.datastructuresalgorithms.week1.quizz.Percolation;

import org.junit.Assert;
import org.junit.Test;

public class PercolationTest {

    @Test
    public void testPercolation() {
        Percolation percolation = new Percolation(4);
        percolation.open(1,1);
        percolation.open(2,1);
        percolation.open(3,1);
        percolation.open(4,1);
        Assert.assertTrue(percolation.percolates());
    }
}
