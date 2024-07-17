package org.amoseman.wavefunctioncollapse.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a matrix of entropy corresponding to a matrix of fields.
 */
public class EntropyMatrix {
    private final double[] entropy;
    private final List<Integer> indices;

    /**
     * Instantiate a new entropy matrix of the given size.
     * @param size the size of the matrix.
     */
    public EntropyMatrix(int size) {
        this.entropy = new double[size];
        this.indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices);
    }

    /**
     * Get the index of the lowest entropy in the matrix.
     * @return the index.
     */
    public int getIndexOfLowest() {
        int least = 0;
        for (Integer index : indices) {
            if (entropy[least] > entropy[index]) {
                least = index;
            }
        }
        return least;
    }

    /**
     * Set the entropy at the provided index.
     * @param index the index.
     * @param e the entropy.
     */
    public void set(int index, double e) {
        entropy[index] = e;
    }
}
