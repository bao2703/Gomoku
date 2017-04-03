package com.neptune;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

/**
 * Created by Neptune on 3/18/2017.
 */
public class State {
    public Mark[][] board;
    private Stack<Move> moveHistory;
    private HashMap<Move, Integer> mapMoveSuccessors;

    public State() {
        board = new Mark[Rule.SIZE][Rule.SIZE];
        for (Mark[] marks : board) {
            Arrays.fill(marks, Mark.BLANK);
        }
        moveHistory = new Stack<>();
        mapMoveSuccessors = new HashMap<>();
        mapMoveSuccessors.put(new Move(5, 5), 0);
    }

    public State(State state) {
        board = new Mark[Rule.SIZE][Rule.SIZE];
        for (int i = 0; i < Rule.SIZE; i++) {
            System.arraycopy(state.board[i], 0, board[i], 0, Rule.SIZE);
        }
        moveHistory = new Stack<>();
        for (Move move : state.moveHistory) {
            moveHistory.add(new Move(move.row, move.col));
        }
        mapMoveSuccessors = new HashMap<>(state.mapMoveSuccessors);
    }

    public void performMove(Move move) {
        if (board[move.row][move.col] != Mark.BLANK) return;
        board[move.row][move.col] = getCurrentPlayer();
        moveHistory.push(move);
        mapMoveSuccessors.remove(move);
        generateMoveSuccessors();
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

    private void generateMoveSuccessors() {
        ArrayList<Move> generatedMoves = getLastMove().generateMove(Rule.RADIUS);
        for (Move generatedMove : generatedMoves) {
            if (isValidMove(generatedMove)) {
                mapMoveSuccessors.put(generatedMove, 0);
            }
        }
    }

    public HashMap<Move, Integer> getMoveSuccessors() {
        return mapMoveSuccessors;
    }

    public boolean canFiveInARow(int index) {
        return index + Rule.WIN_REQUIRED <= Rule.SIZE;
    }

    public GameState checkState() {
        if (moveHistory.empty()) return GameState.ON_GOING;
        if (moveHistory.size() == Math.pow(Rule.SIZE, 2)) return GameState.DRAW;
        Move lastMove = getLastMove();
        Mark player = getPlayer(lastMove.row, lastMove.col);
        if (hasHorizontalWin(lastMove, player) || hasVerticalWin(lastMove, player) || hasDiagonalPrimaryWin(lastMove, player) || hasDiagonalSubWin(lastMove, player)) {
            return player == Mark.MAX ? GameState.MAX_WIN : GameState.MIN_WIN;
        }
        return GameState.ON_GOING;
    }

    public boolean hasHorizontalWin(Move move, Mark player) {
        int count = 1;
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.col + i)) break;
            if (board[move.row][move.col + i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.col - i)) break;
            if (board[move.row][move.col - i] == player)
                count = count + 1;
            else break;
        }
        return count >= Rule.WIN_REQUIRED;
    }

    public boolean hasVerticalWin(Move move, Mark player) {
        int count = 1;
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row + i)) break;
            if (board[move.row + i][move.col] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row - i)) break;
            if (board[move.row - i][move.col] == player)
                count = count + 1;
            else break;
        }
        return count >= Rule.WIN_REQUIRED;
    }

    public boolean hasDiagonalPrimaryWin(Move move, Mark player) {
        int count = 1;
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row + i) || isOutOfRange(move.col + i)) break;
            if (board[move.row + i][move.col + i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row - i) || isOutOfRange(move.col - i)) break;
            if (board[move.row - i][move.col - i] == player)
                count = count + 1;
            else break;
        }
        return count >= Rule.WIN_REQUIRED;
    }

    public boolean hasDiagonalSubWin(Move move, Mark player) {
        int count = 1;
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row + i) || isOutOfRange(move.col - i)) break;
            if (board[move.row + i][move.col - i] == player)
                count = count + 1;
            else break;
        }
        for (int i = 1; i < Rule.WIN_REQUIRED; i++) {
            if (isOutOfRange(move.row - i) || isOutOfRange(move.col + i)) break;
            if (board[move.row - i][move.col + i] == player)
                count = count + 1;
            else break;
        }
        return count >= Rule.WIN_REQUIRED;
    }

    public boolean isOutOfRange(int i) {
        return i < 0 || i >= Rule.SIZE;
    }
}
