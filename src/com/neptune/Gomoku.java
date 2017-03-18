package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Gomoku {
    public State state;

    public Gomoku() {
        state = new State();
    }

    public boolean performMove(int row, int col) {
        return state.performMove(row, col);
    }
}
