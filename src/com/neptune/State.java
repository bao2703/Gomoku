package com.neptune;

import java.util.*;

/**
 * Created by Neptune on 3/18/2017.
 */
public class State {
    public Mark[][] board;
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
        for (Move move : state.moveHistory) {
            performMove(move.row, move.col);
        }
    }

    public void performMove(int row, int col) {
        if (board[row][col] != Mark.BLANK) return;
        board[row][col] = getCurrentPlayer();
        moveHistory.push(new Move(row, col));
    }

    public Mark getCurrentPlayer() {
        return moveHistory.size() % 2 == 0 ? Mark.MIN : Mark.MAX;
    }

    public Mark getPlayer(int row, int col) {
        Move move = new Move(row, col);
        for (int i = 0; i < moveHistory.size(); i++) {
            if (moveHistory.get(i).equals(move)) {
                return i % 2 == 0 ? Mark.MIN : Mark.MAX;
            }
        }
        return null;
    }

    public Move getLastMove() {
        if (moveHistory.isEmpty())
            return null;
        return moveHistory.peek();
    }

    public boolean isValidMove(Move move) {
        return board[move.row][move.col] == Mark.BLANK;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < Rule.SIZE; i++) {
            for (int j = 0; j < Rule.SIZE; j++) {
                if (board[i][j] == Mark.BLANK) {
                    output.append("[ ]");
                } else if (board[i][j] == Mark.MAX) {
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
        int center = Rule.SIZE / 2;
        if (moveHistory.isEmpty()) {
            State state = new State();
            state.performMove(center, center);
            result.add(state);
        } else {
            for (Map.Entry<Move, Integer> kv : getMoveSuccessors().entrySet()) {
                State state = new State(this);
                state.performMove(kv.getKey().row, kv.getKey().col);
                result.add(state);
            }
        }
        return result;
    }

    public HashMap<Move, Integer> getMoveSuccessors() {
        HashMap<Move, Integer> moveMap = new HashMap<>();
        for (Move move : moveHistory) {
            ArrayList<Move> generatedMoves = move.generateMove(Rule.RADIUS);
            for (Move generatedMove : generatedMoves) {
                if (isValidMove(generatedMove)) {
                    moveMap.put(generatedMove, 0);
                }
            }
        }
        return moveMap;
    }

    public boolean canFiveInARow(int index) {
        return index + Rule.WIN_REQUIRED <= Rule.SIZE;
    }
}
