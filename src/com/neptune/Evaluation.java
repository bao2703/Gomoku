package com.neptune;

/**
 * Created by Neptune on 3/18/2017.
 */
public class Evaluation {
    public int computeHeuristic(State state) {
        int heuristic = 0;
        for (int row = 0; row < Rule.SIZE; row++) {
            for (int col = 0; col < Rule.SIZE; col++) {
                heuristic += computeHorizontal(state, row, col);
                heuristic += computeVertical(state, row, col);
                heuristic += computeDiagonalPrimary(state, row, col);
                heuristic += computeDiagonalSub(state, row, col);
            }
        }
        return heuristic;
    }

    public int computeHorizontal(State state, int currentRow, int currentCol) {
        if (!state.canFiveInARow(currentCol))
            return 0;
        Counter counter = new Counter();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            counter.exec(state.board[currentRow][currentCol + i]);
        }
        return counter.getHeuristic();
    }

    public int computeVertical(State state, int currentRow, int currentCol) {
        if (!state.canFiveInARow(currentRow))
            return 0;
        Counter counter = new Counter();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            counter.exec(state.board[currentRow + i][currentCol]);
        }
        return counter.getHeuristic();
    }

    public int computeDiagonalPrimary(State state, int currentRow, int currentCol) {
        if (!state.canFiveInARow(currentCol) || !state.canFiveInARow(currentRow)) {
            return 0;
        }
        Counter counter = new Counter();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            counter.exec(state.board[currentRow + i][currentCol + i]);
        }
        return counter.getHeuristic();
    }

    public int computeDiagonalSub(State state, int currentRow, int currentCol) {
        if (currentRow < Rule.WIN_REQUIRED - 1 || !state.canFiveInARow(currentCol)) {
            return 0;
        }
        Counter counter = new Counter();
        for (int i = 0; i < Rule.WIN_REQUIRED; i++) {
            counter.exec(state.board[currentRow - i][currentCol + i]);
        }
        return counter.getHeuristic();
    }

    private class Counter {
        private int min;
        private int max;

        public Counter() {
            this(0, 0);
        }

        public Counter(int o, int x) {
            this.min = o;
            this.max = x;
        }

        public void exec(Mark mark) {
            if (mark == Mark.MIN) {
                min = min + 1;
            }
            if (mark == Mark.MAX) {
                max = max + 1;
            }
        }

        public int getHeuristic() {
            int heuristic = 0;
            if (max * min == 0 && max != min) {
                if (min == 3 || min == 4) heuristic -= simpleDef(min);
                else if (min == 5) heuristic -= Integer.MIN_VALUE;
                else heuristic -= simple(min);
                heuristic += simple(max);
            }
            return heuristic;
        }

        private int simpleDef(int num) {
            return simple(num) * 2;
        }

        private int simple(int num) {
            return (int) Math.pow(4, num);
        }
    }
}
