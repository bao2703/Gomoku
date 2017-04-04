package com.neptune;

/**
 * Created by Neptune on 3/22/2017.
 */
public class Counter {
    private int min;
    private int max;

    public Counter() {
        this(0, 0);
    }

    public Counter(int max, int min) {
        this.min = max;
        this.max = min;
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
            if (Rule.PLAYER_FIRST) {
                heuristic -= simpleDef(min);
                heuristic += simple(max);
            } else {
                heuristic -= simple(min);
                heuristic += simpleDef(max);
            }
        }
        return heuristic;
    }

    private int simpleDef(int num) {
        int result = simple(num);
        if (num == 4) result *= 2;
        return result;
    }

    private int simple(int num) {
        return (int) Math.pow(4, num);
    }
}