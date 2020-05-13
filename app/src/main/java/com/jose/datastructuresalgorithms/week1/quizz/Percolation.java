package com.jose.datastructuresalgorithms.week1.quizz;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final boolean[][] matrix;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int openSites = 0;
    private final int virtualTop;
    private final int virtualBottom;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.matrix = new boolean[n][n];
        weightedQuickUnionUF = new WeightedQuickUnionUF((n * n) + 2);
        virtualTop = n * n;
        virtualBottom = n * n + 1;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRangeOfEntry(row);
        validateRangeOfEntry(col);
        int matrixRow = row - 1;
        int matrixCol = col - 1;
        int siteValue = getSiteValue(matrixRow, matrixCol);
        if (!matrix[matrixRow][matrixCol]) {
            matrix[matrixRow][matrixCol] = true;
            connectNeighbor(siteValue, matrixRow, matrixCol + 1);
            connectNeighbor(siteValue, matrixRow, matrixCol - 1);
            if (row == 1) {
                weightedQuickUnionUF.union(siteValue, virtualTop);
            } else {
                connectNeighbor(siteValue, matrixRow - 1, matrixCol);
            }

            if (row == n) {
                weightedQuickUnionUF.union(siteValue, virtualBottom);
            } else {
                connectNeighbor(siteValue, matrixRow + 1, matrixCol);
            }
            openSites++;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRangeOfEntry(row);
        validateRangeOfEntry(col);
        return matrix[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRangeOfEntry(row);
        validateRangeOfEntry(col);
        if (isOpen(row, col)) {
            int siteValue = getSiteValue(row - 1, col - 1);
            return weightedQuickUnionUF.find(siteValue) == weightedQuickUnionUF.find(virtualTop);
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return weightedQuickUnionUF.find(virtualTop) == weightedQuickUnionUF.find(virtualBottom);
    }

    private void validateRangeOfEntry(int entry) {
        if (entry < 1 || entry > n) {
            throw new IllegalArgumentException();
        }
    }

    private int getSiteValue(int row, int col) {
        return (n * row) + col;
    }

    private void connectNeighbor(int siteValue, int row, int col) {
        if (col < n && col >= 0 && row < n && row >= 0) {
            if (isOpen(row + 1, col + 1)) {
                weightedQuickUnionUF.union(siteValue, getSiteValue(row, col));
            }
        }
    }
}
