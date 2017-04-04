package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Evaluation {
    public int computeHeuristic(State state) {
        int heuristic = 0;
        for (int row = 0; row < Rule.SIZE; row++) {
            for (int col = 0; col < Rule.SIZE; col++) {
                Counter counterHorizontal = new Counter();
                Counter counterVertical = new Counter();
                Counter counterDiagonalPrimary = new Counter();
                Counter counterDiagonalSub = new Counter();
                for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
                    if (state.canFiveInARow(col)) counterHorizontal.exec(state.board[row][col + i]);
                    if (state.canFiveInARow(row)) counterVertical.exec(state.board[row + i][col]);
                    if (state.canFiveInARow(col) && state.canFiveInARow(row))
                        counterDiagonalPrimary.exec(state.board[row + i][col + i]);
                    if (row >= Rule.WIN_REQUIRED - 1 && state.canFiveInARow(col))
                        counterDiagonalSub.exec(state.board[row - i][col + i]);
                }
                heuristic += counterHorizontal.getHeuristic() + counterVertical.getHeuristic() +
                        counterDiagonalPrimary.getHeuristic() + counterDiagonalSub.getHeuristic();
            }
        }
        return heuristic;
    }
}