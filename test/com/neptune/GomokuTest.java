package com.neptune;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

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