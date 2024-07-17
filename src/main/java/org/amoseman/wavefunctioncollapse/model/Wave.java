package org.amoseman.wavefunctioncollapse.model;

import java.util.Arrays;

public class Wave {
    private final int states;
    private final double[] values;
    private double sum;

    public Wave(int states) {
        this.states = states;
        this.values = new double[states];
        for (int i = 0; i < states; i++) {
            values[i] = 1.0 / states;
        }
        this.sum = calculateSum();
    }

    public void update(Field[] fields) {
        Arrays.fill(values, 0);
        for (int i = 0; i < fields.length; i++) {
            int state = fields[i].state();
            if (state == 0) {
                continue;
            }
            values[state - 1] += 1.0 / fields.length;
        }
        sum = calculateSum();
    }

    private double calculateSum() {
        double s = 0;
        for (double w : values) {
            s += w;
        }
        return s;
    }

    public double getSum() {
        return sum;
    }

    public double getWeight(int index) {
        return values[index];
    }
}
