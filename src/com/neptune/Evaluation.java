package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Evaluation {
    public int computeHeuristic(State state) {
        int heuristic = 0;
        for (int row = 0; row < Rule.SIZE; row++) {
            for (int col = 0; col < Rule.SIZE; col++) {
                heuristic += computeHorizontal(state, row, col);
                heuristic += computeVertical(state, row, col);
                heuristic += computeDiagonalPrimary(state, row, col);
                heuristic += computeDiagonalSub(state, row, col);
            }
        }
        return heuristic;
    }

    public int computeHorizontal(State state, int currentRow, int currentCol) {
        if (!state.canFiveInARow(currentCol))
            return 0;
        Counter counter = new Counter();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            counter.exec(state.board[currentRow][currentCol + i]);
        }
        return counter.getHeuristic();
    }

    public int computeVertical(State state, int currentRow, int currentCol) {
        if (!state.canFiveInARow(currentRow))
            return 0;
        Counter counter = new Counter();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            counter.exec(state.board[currentRow + i][currentCol]);
        }
        return counter.getHeuristic();
    }

    public int computeDiagonalPrimary(State state, int currentRow, int currentCol) {
        if (!state.canFiveInARow(currentCol) || !state.canFiveInARow(currentRow)) {
            return 0;
        }
        Counter counter = new Counter();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            counter.exec(state.board[currentRow + i][currentCol + i]);
        }
        return counter.getHeuristic();
    }

    public int computeDiagonalSub(State state, int currentRow, int currentCol) {
        if (currentRow < Rule.WIN_REQUIRED - 1 || !state.canFiveInARow(currentCol)) {
            return 0;
        }
        Counter counter = new Counter();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            counter.exec(state.board[currentRow - i][currentCol + i]);
        }
        return counter.getHeuristic();
    }
}
