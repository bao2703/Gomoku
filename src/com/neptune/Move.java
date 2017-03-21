package com.neptune;

import java.util.ArrayList;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Move {
    public int row;
    public int col;

    public Move() {
        this(0, 0);
    }

    public Move(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Move(Move move) {
        this(move.row, move.col);
    }

    @Override
    public String toString() {
        return row + " " + col;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        Move other = (Move) obj;
        if (this.row != other.row) return false;
        if (this.col != other.col) return false;
        return true;
    }

    public Move createMoveUp() {
        return new Move(row - 1, col);
    }

    public Move createMoveDown() {
        return new Move(row + Rule.RADIUS, col);
    }

    public boolean isValid() {
        return !(row < 0 || row >= Rule.SIZE || col < 0 || col >= Rule.SIZE);
    }

    public ArrayList<Move> generateMove(int radius) {
        if (!this.isValid()) {
            return null;
        }
        ArrayList<Move> result = new ArrayList<>();
        int top = row - radius;
        int bottom = col + radius;
        for (int i = top; i <= bottom; i++) {
            for (int j = top; j <= bottom; j++) {
                if (i == row && j == col) continue;
                Move move = new Move(i, j);
                if (move.isValid())
                    result.add(move);
            }
        }
        return result;
    }
}
