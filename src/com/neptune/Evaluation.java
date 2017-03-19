package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Evaluation {
    private int simpleDef(int num) {
        int result = simple(num);
        if (num == 4) {
            return result * 2;
        }
        return result;
    }

    private int simple(int num) {
        if (num == 0) {
            return 0;
        }
        return (int) Math.pow(4, num);
    }

    public int computeHeuristic(State state) {
        int heuristic = 0;
        for (int row = 0; row < Rule.SIZE; row++) {
            for (int col = 0; col < Rule.SIZE - Rule.WIN_REQUIRED; col++) {
                heuristic += computeHorizontal(state, row, col);
            }
        }

        for (int row = 0; row < Rule.SIZE - Rule.WIN_REQUIRED; row++) {
            for (int col = 0; col < Rule.SIZE; col++) {
                heuristic += computeVertical(state, row, col);
            }
        }

        for (int row = 0; row < Rule.SIZE - Rule.WIN_REQUIRED - 1; row++) {
            for (int col = 0; col < Rule.SIZE - Rule.WIN_REQUIRED - 1; col++) {
                heuristic += computeDiagonalPrimary(state, row, col);
            }
        }

        for (int row = Rule.WIN_REQUIRED - 1; row < Rule.SIZE; row++)
            for (int col = 0; col < Rule.SIZE - 4; col++) {
                heuristic += computeDiagonalSub(state, row, col);
            }

        return heuristic;
    }

    public int computeHorizontal(State state, int currentRow, int currentCol) {
        Count count = new Count();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            count.exec(state.board[currentRow][currentCol + i]);
        }
        return count.getHeuristic();
    }

    public int computeVertical(State state, int currentRow, int currentCol) {
        Count count = new Count();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            count.exec(state.board[currentRow + i][currentCol]);
        }
        return count.getHeuristic();
    }

    public int computeDiagonalPrimary(State state, int currentRow, int currentCol) {
        Count count = new Count();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            count.exec(state.board[currentRow + i][currentCol + i]);
        }
        return count.getHeuristic();
    }

    public int computeDiagonalSub(State state, int currentRow, int currentCol) {
        Count count = new Count();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            count.exec(state.board[currentRow - i][currentCol + i]);
        }
        return count.getHeuristic();
    }

    private class Count {
        private int o;
        private int x;

        public Count() {
            this(0, 0);
        }

        public Count(int o, int x) {
            this.o = o;
            this.x = x;
        }

        public void exec(Mark mark) {
            if (mark == Mark.O) {
                o = o + 1;
            }
            if (mark == Mark.X) {
                x = x + 1;
            }
        }

        public int getHeuristic() {
            int heuristic = 0;
            if (x * o == 0 && x != o) {
                heuristic -= simpleDef(o);
                heuristic += simpleDef(x);
            }
            return heuristic;
        }
    }
}
