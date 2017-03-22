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
            if (min == 4) heuristic -= simpleDef(min);
            else heuristic -= simple(min);
            if (max == 4) heuristic += simpleDef(min);
            else heuristic += simple(max);
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