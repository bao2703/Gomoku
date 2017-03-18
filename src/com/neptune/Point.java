package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Point {
    public int row;
    public int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Point(Point move) {
        this(move.row, move.col);
    }

    @Override
    public String toString() {
        return row + " " + col;
    }
}
