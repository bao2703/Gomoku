package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Gomoku {
    public State state;

    public Gomoku() {
        state = new State();
    }

    public void performMove(int row, int col) {
        state.performMove(row, col);
    }

    public Move getBestMove() {
        AlphaBeta alphaBeta = new AlphaBeta();
        alphaBeta.exec(state, Rule.MAX_DEPTH);
        return null;
    }
}
