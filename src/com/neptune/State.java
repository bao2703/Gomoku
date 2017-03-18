package com.neptune;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Stack;
import java.util.Arrays;

/**
 * Created by Neptune on 3/18/2017.
 */
public class State {
    public Mark[][] board;
    private int heuristic;
    private Stack<Move> moveHistory;

    public State() {
        board = new Mark[Rule.SIZE][Rule.SIZE];
        for (Mark[] marks : board) {
            Arrays.fill(marks, Mark.BLANK);
        }
        moveHistory = new Stack<>();
    }

    public State(final State state) {
        this();
        moveHistory.addAll(state.moveHistory);
    }

    public boolean performMove(int row, int col) {
        if (board[row][col] != Mark.BLANK) {
            return false;
        }
        board[row][col] = getCurrentPlayer();
        moveHistory.push(new Move(row, col));
        return true;
    }

    public Mark getCurrentPlayer() {
        return moveHistory.size() % 2 == 0 ? Mark.O : Mark.X;
    }

    public Mark getPlayer(int row, int col) {
        Move move = new Move(row, col);
        for (int i = 0; i < moveHistory.size(); i++) {
            if (moveHistory.get(i).equals(move)) {
                return i % 2 == 0 ? Mark.O : Mark.X;
            }
        }
        return null;
    }

    public static Comparator<State> HeuristicComparator = new Comparator<State>() {
        @Override
        public int compare(State a, State b) {
            return b.getHeuristic() - a.getHeuristic();
        }
    };

    public int getHeuristic() {
        return heuristic;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < Rule.SIZE; i++) {
            for (int j = 0; j < Rule.SIZE; j++) {
                if (board[i][j] == Mark.BLANK) {
                    output.append("[ ]");
                } else if (board[i][j] == Mark.X) {
                    output.append("[X]");
                } else {
                    output.append("[O]");
                }

                output.append(" ");
            }
            output.append("\n");
        }
        return output.toString();
    }

    public ArrayList<State> getSuccessors() {
        ArrayList<State> result = new ArrayList<>();
        return result;
    }
}
