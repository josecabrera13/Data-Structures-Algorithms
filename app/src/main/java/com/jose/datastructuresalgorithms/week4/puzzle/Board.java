package com.jose.datastructuresalgorithms.week4.puzzle;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private final int[][] tiles;
    private List<Board> neighbors;
    private int hamming = 0;
    private int manhattan = 0;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
        final int dimension = tiles.length;
        this.tiles = new int[dimension][dimension];
        countDistances(tiles);
    }

    // string representation of this board
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(dimension());

        for (int i = 0; i < dimension(); i++) {
            stringBuilder.append("\n");
            for (int j = 0; j < dimension(); j++) {
                stringBuilder.append(" ").append(tiles[i][j]);
            }
        }

        return stringBuilder.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        return hamming;

    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        return manhattan;
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Board)) return false;
        Board board = (Board) o;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        if (neighbors != null) {
            return neighbors;
        }
        neighbors = new LinkedList<>();
        createNeighbors();
        return neighbors;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int[][] twinTiles = blocksCopy();

        if (tiles[0][0] != 0 && tiles[0][1] != 0) {
            twinTiles[0][0] = tiles[0][1];
            twinTiles[0][1] = tiles[0][0];
        } else {
            twinTiles[1][0] = tiles[1][1];
            twinTiles[1][1] = tiles[1][0];
        }

        return new Board(twinTiles);
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }

    private int[] findEmptySpace() {
        //locate empty space "0"
        //[row][col]
        int boardSize = tiles.length;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                if (tiles[row][col] == 0) {
                    return new int[]{row, col};
                }
            }
        }
        throw new IllegalArgumentException();
    }

    private void createNeighbors() {
        int[] emptySpacePosition = findEmptySpace();
        addNeighbor(emptySpacePosition, new int[]{emptySpacePosition[0], emptySpacePosition[1] - 1});
        addNeighbor(emptySpacePosition, new int[]{emptySpacePosition[0], emptySpacePosition[1] + 1});
        addNeighbor(emptySpacePosition, new int[]{emptySpacePosition[0] + 1, emptySpacePosition[1]});
        addNeighbor(emptySpacePosition, new int[]{emptySpacePosition[0] - 1, emptySpacePosition[1]});
    }

    private void addNeighbor(int[] emptySpacePosition, int[] swapPosition) {
        if ((swapPosition[0] >= 0 && swapPosition[0] < dimension()) && (swapPosition[1] >= 0 && swapPosition[1] < dimension())) {
            int[][] neighborTiles = blocksCopy();
            neighborTiles[emptySpacePosition[0]][emptySpacePosition[1]] = neighborTiles[swapPosition[0]][swapPosition[1]];
            neighborTiles[swapPosition[0]][swapPosition[1]] = 0;
            neighbors.add(new Board(neighborTiles));
        }
    }

    private int[][] blocksCopy() {
        int[][] blocksCopy = new int[dimension()][dimension()];
        for (int i = 0; i < dimension(); i++) {
            if (dimension() >= 0) System.arraycopy(tiles[i], 0, blocksCopy[i], 0, dimension());
        }
        return blocksCopy;
    }

    private void countDistances(int[][] tiles) {
        // [row][col]
        int boardSize = tiles.length;
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                int currentNumber = tiles[row][col];
                this.tiles[row][col] = currentNumber;
                int expectedNumber = 1 + col + (row * boardSize);
                if (currentNumber != expectedNumber && currentNumber != 0) {
                    hamming++;
                    int expectedCol = getExpectedCol(currentNumber, boardSize);
                    int expectedRow = getExpectedRow(currentNumber, boardSize);
                    manhattan += Math.abs(expectedCol - col) + Math.abs(expectedRow - row);
                }
            }
        }
    }

    private int getExpectedCol(int currentNumber, int boardLength) {
        int expectedCol = currentNumber % boardLength;
        if (expectedCol == 0) {
            return boardLength - 1;
        } else {
            return expectedCol - 1;
        }
    }

    private int getExpectedRow(int currentNumber, int boardLength) {
        int module = currentNumber % boardLength;
        if (module == 0) {
            return (currentNumber / boardLength) - 1;
        } else {
            return (currentNumber / boardLength);
        }
    }
}
