package com.neptune;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Neptune on 3/18/2017.
 */
class StateTest {
    State state;

    @BeforeEach
    void setUp() {
        state = new State();
    }

    @Test
    void getSuccessors_1_in_first_move() {
        ArrayList<State> successors = state.getSuccessors();
        assertEquals(1, successors.size());
    }

    @Test
    void getSuccessors_24_in_second_move() {
        ArrayList<State> successors = state.getSuccessors();
        successors = successors.get(0).getSuccessors();
        assertEquals(24, successors.size());
    }

    @Test
    void performMove() {
        state.performMove(5, 5);
        Assertions.assertEquals(state.getPlayer(5,5), state.board[5][5]);
    }

    @Test
    void getCurrentPlayer_O_first() {
        Mark actual = state.getCurrentPlayer();
        assertEquals(Mark.O, actual);
    }

    @Test
    void getCurrentPlayer_O_first_X_seccond() {
        state.performMove(0, 0);
        Mark actual = state.getCurrentPlayer();
        assertEquals(Mark.X, actual);
    }

    @Test
    public void checkHorizontal_has_winner() {
        State state = new State();
        for (int i = 0; i < 5; i++) {
            state.board[0][i] = Mark.X;
        }

        boolean actual = state.checkHorizontal(new Move(0, 0), Mark.X);
        assertEquals(true, actual);
    }

    @Test
    public void checkVertical_has_winner() {
        State state = new State();
        for (int i = 0; i < 5; i++) {
            state.board[i][0] = Mark.X;
        }

        boolean actual = state.checkVertical(new Move(0, 0), Mark.X);
        assertEquals(true, actual);
    }

    @Test
    public void checkDiagonalPrimary_has_winner() {
        State state = new State();
        for (int i = 0; i < 5; i++) {
            state.board[i][i] = Mark.X;
        }

        boolean actual = state.checkDiagonalPrimary(new Move(0, 0), Mark.X);
        assertEquals(true, actual);
    }

    @Test
    public void checkDiagonalSub_has_winner() {
        State state = new State();
        for (int i = 0; i < 5; i++) {
            state.board[i][Rule.SIZE - i - 1] = Mark.X;
        }

        boolean actual = state.checkDiagonalSub(new Move(4, 6), Mark.X);
        assertEquals(true, actual);
    }
}