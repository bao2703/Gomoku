package com.neptune;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Neptune on 3/18/2017.
 */
public class AlphaBeta {
    private Evaluation evaluation;
    private HashMap<Integer, Move> trackingMove;

    public AlphaBeta() {
        evaluation = new Evaluation();
        trackingMove = new HashMap<>();
    }

    public Move exec(State currentState, int depth) {
        exec(currentState, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
        return trackingMove.get(Rule.MAX_DEPTH);
    }

    private int exec(State currentState, int alpha, int beta, int depth) {
        GameState gameState = currentState.checkState();
        if (gameState == GameState.MAX_WIN) return 1000000 + depth;
        else if (gameState == GameState.MIN_WIN) return -1000000 - depth;
        else if (gameState == GameState.DRAW) return 0;

        if (depth == 0) {
            return evaluation.computeHeuristic(currentState);
        }
        HashMap<Move, Integer> mapMoveSuccessors = currentState.getMoveSuccessors();
        int bestValue;
        if (currentState.getCurrentPlayer() == Mark.MAX) {
            bestValue = alpha;
            for (Map.Entry<Move, Integer> kv : mapMoveSuccessors.entrySet()) {
                Move move = kv.getKey();
                State successorState = new State(currentState);
                successorState.performMove(move);
                int childValue = exec(successorState, bestValue, beta, depth - 1);
                if (childValue > bestValue) {
                    bestValue = childValue;
                    trackingMove.put(depth, move);
                }
                if (bestValue >= beta) {
                    break;
                }
            }
        } else {
            bestValue = beta;
            for (Map.Entry<Move, Integer> kv : mapMoveSuccessors.entrySet()) {
                Move move = kv.getKey();
                State successorState = new State(currentState);
                successorState.performMove(move);
                int childValue = exec(successorState, alpha, bestValue, depth - 1);
                if (childValue < bestValue) {
                    bestValue = childValue;
                    trackingMove.put(depth, move);
                }
                if (bestValue <= alpha) {
                    break;
                }
            }
        }
        return bestValue;
    }
}