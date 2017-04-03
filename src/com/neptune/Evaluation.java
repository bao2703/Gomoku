package com.neptune;

import java.util.HashMap;
import java.util.Map;

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

    public void computeSimpleOrder(State state) {
        int sum;
        HashMap<Move, Integer> mapMoveSuccessors = state.getMoveSuccessors();
        for (Map.Entry<Move, Integer> kv : mapMoveSuccessors.entrySet()) {
            sum = 0;
            Move move = kv.getKey();
            sum += simpleOrderHorizontal(state, move);
            sum += simpleOrderVertical(state, move);
            sum += simpleOrderDiagonalPrimary(state, move);
            sum += simpleOrderDiagonalSub(state, move);
            kv.setValue(sum);
        }
    }

    public int simpleOrderHorizontal(State state, Move move) {
        int count = 0;
        Mark player = state.getCurrentPlayer();
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (state.isOutOfRange(move.col + i)) break;
            if (state.board[move.row][move.col + i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (state.isOutOfRange(move.col - i)) break;
            if (state.board[move.row][move.col - i] != player)
                count = count + 1;
            else break;
        }
        return (int) Math.pow(2, count);
    }

    public int simpleOrderVertical(State state, Move move) {
        int count = 0;
        Mark player = state.getCurrentPlayer();
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (state.isOutOfRange(move.row + i)) break;
            if (state.board[move.row + i][move.col] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (state.isOutOfRange(move.row - i)) break;
            if (state.board[move.row - i][move.col] == player)
                count = count + 1;
            else break;
        }
        return (int) Math.pow(2, count);
    }

    public int simpleOrderDiagonalPrimary(State state, Move move) {
        int count = 0;
        Mark player = state.getCurrentPlayer();
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (state.isOutOfRange(move.row + i) || state.isOutOfRange(move.col + i)) break;
            if (state.board[move.row + i][move.col + i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (state.isOutOfRange(move.row - i) || state.isOutOfRange(move.col - i)) break;
            if (state.board[move.row - i][move.col - i] == player)
                count = count + 1;
            else break;
        }
        return (int) Math.pow(2, count);
    }

    public int simpleOrderDiagonalSub(State state, Move move) {
        int count = 0;
        Mark player = state.getCurrentPlayer();
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (state.isOutOfRange(move.row + i) || state.isOutOfRange(move.col - i)) break;
            if (state.board[move.row + i][move.col - i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (state.isOutOfRange(move.row - i) || state.isOutOfRange(move.col + i)) break;
            if (state.board[move.row - i][move.col + i] == player)
                count = count + 1;
            else break;
        }
        return (int) Math.pow(2, count);
    }
}
