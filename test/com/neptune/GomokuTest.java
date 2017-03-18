package com.neptune;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Created by Neptune on 3/18/2017.
 */
class GomokuTest {
    Gomoku gomoku;

    @BeforeEach
    void setUp() {
        gomoku = new Gomoku();
    }

    @Test
    void getBestMove() {
        gomoku.getBestMove();
        assertEquals(false, true);
    }
}