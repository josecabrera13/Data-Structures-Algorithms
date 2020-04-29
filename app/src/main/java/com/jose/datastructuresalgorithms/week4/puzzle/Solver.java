package com.jose.datastructuresalgorithms.week4.puzzle;

import java.util.ArrayList;
import java.util.List;

public class Solver {
    private SearchNode solution;

    private class SearchNode implements Comparable<SearchNode> {
        private final Board board;
        private final SearchNode predecessor;
        private final int moves;
        private final int priority;

        SearchNode(Board board, SearchNode predecessor) {
            this.board = board;
            this.predecessor = predecessor;
            this.moves = predecessor == null ? 0 : this.predecessor.moves + 1;
            this.priority = board.manhattan() + moves;
        }

        void insertNeighbors(MinPQ<SearchNode> priorityQueue) {
            for (Board neighbor : board.neighbors()) {
                if (predecessor != null && neighbor.equals(predecessor.board)) {
                    continue;
                }
                priorityQueue.insert(new SearchNode(neighbor, this));
            }

        }

        @Override
        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        MinPQ<SearchNode> priorityQueue = new MinPQ<>();
        MinPQ<SearchNode> twinPriorityQueue = new MinPQ<>();
        priorityQueue.insert(new SearchNode(initial, null));
        twinPriorityQueue.insert(new SearchNode(initial.twin(), null));
        while (true) {
            SearchNode searchNode = priorityQueue.delMin();
            SearchNode twinSearchNode = twinPriorityQueue.delMin();

            if (searchNode.board.isGoal()) {
                solution = searchNode;
                break;
            } else if (twinSearchNode.board.isGoal()) {
                solution = null;
                break;
            }
            searchNode.insertNeighbors(priorityQueue);
            twinSearchNode.insertNeighbors(twinPriorityQueue);
        }
    }

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return solution != null;
    }

    // min number of moves to solve initial board
    public int moves() {
        return solution != null ? solution.moves : 0;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        List<Board> sequenceBoard = new ArrayList<>();
        SearchNode searchNode = solution;
        while (searchNode != null) {
            sequenceBoard.add(searchNode.board);
            searchNode = searchNode.predecessor;
        }
        return sequenceBoard;
    }

    // test client (see below)
    public static void main(String[] args) {
       /* In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }*/
    }
}


