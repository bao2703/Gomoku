package com.neptune;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        return alphaBeta.exec(state, Rule.MAX_DEPTH).getLastMove();
    }

    public void writeState(File file) throws IOException {
        BufferedWriter bufferedWriter;
        bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        bufferedWriter.write(state.getLastMove().toString());
        bufferedWriter.newLine();
        bufferedWriter.close();
    }
}
