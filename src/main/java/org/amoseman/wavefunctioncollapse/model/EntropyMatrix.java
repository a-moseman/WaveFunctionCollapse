package org.amoseman.wavefunctioncollapse.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntropyMatrix {
    private final int size;
    private final double[] entropy;
    private final List<Integer> indices;

    public EntropyMatrix(int size) {
        this.size = size;
        this.entropy = new double[size];
        this.indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
    }

    public int getIndexOfLowest() {
        int least = 0;
        for (Integer index : indices) {
            if (entropy[least] > entropy[index]) {
                least = index;
            }
        }
        return least;
    }

    public double get(int index) {
        return entropy[index];
    }

    public void set(int index, double e) {
        entropy[index] = e;
    }
}
