package com.neptune;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neptune on 3/21/2017.
 */
class MoveTest {
    Move move;

    @BeforeEach
    void setUp() {
        move = new Move();
    }

    @Test
    void isValid() {
        move.row = -1;
        move.col = -1;
        assertEquals(false, move.isValid());

        move.row = 1;
        move.col = -1;
        assertEquals(false, move.isValid());

        move.row = Rule.SIZE;
        move.col = Rule.SIZE;
        assertEquals(false, move.isValid());

        move.row = Rule.SIZE;
        move.col = Rule.SIZE / 2;
        assertEquals(false, move.isValid());

        move.row = Rule.SIZE / 2;
        move.col = Rule.SIZE;
        assertEquals(false, move.isValid());
    }

    @Test
    void generateMove_radius_1_should_return_8_moves() {
        move.row = Rule.SIZE / 2;
        move.col = Rule.SIZE / 2;
        int radius = 1;
        ArrayList<Move> generatedMoves = move.generateMove(radius);

        assertEquals(8, generatedMoves.size());
    }

    @Test
    void generateMove_radius_2_should_return_24_moves() {
        move.row = Rule.SIZE / 2;
        move.col = Rule.SIZE / 2;
        int radius = 2;
        ArrayList<Move> generatedMoves = move.generateMove(radius);

        assertEquals(24, generatedMoves.size());
    }
}