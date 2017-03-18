package com.neptune;

import java.util.ArrayList;

/**
 * Created by Neptune on 3/18/2017.
 */
public class AlphaBeta {

    public int exec(State currentState, int depth) {
        return exec(currentState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
    }

    private int exec(State currentState, int alpha, int beta, int depth) {
        ArrayList<State> successors = currentState.getSuccessors();
        if (successors.isEmpty() || depth == 0) {
            return currentState.getHeuristic();
        }
        int bestValue;
        if (currentState.getCurrentPlayer() == Mark.X) {
            bestValue = alpha;
            for (State successor : successors) {
                bestValue = Math.max(bestValue, exec(successor, bestValue, beta, depth - 1));
                if (bestValue >= beta) {
                    break;
                }
            }
        } else {
            bestValue = beta;
            for (State successor : successors) {
                bestValue = Math.min(bestValue, exec(successor, alpha, bestValue, depth - 1));
                if (bestValue <= alpha) {
                    break;
                }
            }
        }
        return bestValue;
    }
}
