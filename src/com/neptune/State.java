package com.neptune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

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

    public Move getLastMove() {
        if (moveHistory.isEmpty())
            return null;
        return moveHistory.peek();
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
        int center = Rule.SIZE / 2;
        if (moveHistory.isEmpty()) {
            State state = new State();
            state.performMove(center, center);
            result.add(state);
        } else {
            if (moveHistory.size() == 8)
                Rule.RADIUS = 4;
            else if (moveHistory.size() == 11)
                Rule.RADIUS = 5;
            int top = center - Rule.RADIUS;
            int bottom = center + Rule.RADIUS;
            for (int i = top; i <= bottom; i++) {
                for (int j = top; j <= bottom; j++) {
                    if (board[i][j] == Mark.BLANK) {
                        State state = new State(this);
                        state.performMove(i, j);
                        result.add(state);
                    }
                }
            }
        }

        return result;
    }

    public GameState checkState() {
        Move lastMove = getLastMove();
        Mark currentPlayer = getCurrentPlayer();
        if (checkHorizontal(lastMove, currentPlayer) || checkVertical(lastMove, currentPlayer) || checkDiagonalPrimary(lastMove, currentPlayer)
                || checkDiagonalSub(lastMove, currentPlayer)) {
            return currentPlayer == Mark.X ? GameState.X_WIN : GameState.O_WIN;
        }

        if (moveHistory.size() == Math.pow(Rule.SIZE, 2)) {
            return GameState.DRAW;
        }
        return GameState.ON_GOING;
    }

    public boolean checkHorizontal(Move move, Mark player) {
        if (!canFiveInARow(move.col)) {
            return false;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (board[move.row][move.col + i] != player) {
                return false;
            }
        }
        return true;
    }

    public boolean checkVertical(Move move, Mark player) {
        if (!canFiveInAColumn(move.row)) {
            return false;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (board[move.row + i][move.col] != player) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonalPrimary(Move move, Mark player) {
        if (!canFiveInARow(move.col) || !canFiveInAColumn(move.row)) {
            return false;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (board[move.row + i][move.col + i] != player) {
                return false;
            }
        }
        return true;
    }

    public boolean checkDiagonalSub(Move move, Mark player) {
        if (move.row < Rule.WIN_REQUIRED - 1 || !canFiveInARow(move.col)) {
            return false;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (board[move.row - i][move.col + i] != player) {
                return false;
            }
        }
        return true;
    }

    public boolean canFiveInARow(int col) {
        return col + Rule.WIN_REQUIRED <= Rule.SIZE;
    }

    public boolean canFiveInAColumn(int row) {
        return row + Rule.WIN_REQUIRED <= Rule.SIZE;
    }
}
