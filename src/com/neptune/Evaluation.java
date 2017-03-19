package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Evaluation {
    private static int simpleDef(int num) {
        int result = simple(num);
        if (num == 4) {
            return result * 2;
        }
        return result;
    }

    private static int simple(int num) {
        if (num == 0) {
            return 0;
        }
        return (int) Math.pow(4, num);
    }

    public static int computeHeuristic(State state) {
        int heuristic = 0;
        int x;
        int o;
        for (int row = 0; row < Rule.SIZE; row++) {
            for (int col = 0; col < Rule.SIZE - Rule.WIN_REQUIRED; col++) {
                x = 0;
                o = 0;
                for (int i = 0; i < 5; i++) {
                    if (state.board[row][col + i] == Mark.O)
                        o++;
                    if (state.board[row][col + i] == Mark.X)
                        x++;
                }
                if (x * o == 0 && x != o) {
                    heuristic -= simpleDef(o);
                    heuristic += simpleDef(x);
                }
            }
        }

        for (int row = 0; row < Rule.SIZE - Rule.WIN_REQUIRED; row++) {
            for (int col = 0; col < Rule.SIZE; col++) {
                x = 0;
                o = 0;
                for (int i = 0; i < 5; i++) {
                    if (state.board[row + i][col] == Mark.O)
                        o++;
                    if (state.board[row + i][col] == Mark.X)
                        x++;
                }
                if (x * o == 0 && x != o) {
                    heuristic -= simpleDef(o);
                    heuristic += simpleDef(x);
                }
            }
        }

        for (int row = 0; row < Rule.SIZE - Rule.WIN_REQUIRED - 1; row++) {
            for (int col = 0; col < Rule.SIZE - Rule.WIN_REQUIRED - 1; col++) {
                x = 0;
                o = 0;
                for (int i = 0; i < 5; i++) {
                    if (state.board[row + i][col + i] == Mark.O)
                        o++;
                    if (state.board[row + i][col + i] == Mark.X)
                        x++;
                }
                if (x * o == 0 && x != o) {
                    heuristic -= simpleDef(o);
                    heuristic += simpleDef(x);
                }
            }
        }

        for (int row = Rule.WIN_REQUIRED - 1; row < Rule.SIZE; row++)
            for (int col = 0; col < Rule.SIZE - 4; col++) {
                x = 0;
                o = 0;
                for (int i = 0; i < 5; i++) {
                    if (state.board[row - i][col + i] == Mark.O)
                        o++;
                    if (state.board[row - i][col + i] == Mark.X)
                        x++;
                }
                if (x * o == 0 && x != o) {
                    heuristic -= simpleDef(o);
                    heuristic += simpleDef(x);
                }
            }

        return heuristic;
    }
}
