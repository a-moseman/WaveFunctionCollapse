package org.amoseman.wavefunctioncollapse.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a superposition.
 */
public class Field {
    private final int states;
    private final List<Integer> superposition;
    private int state;

    /**
     * Instantiate a new superposition with a given number of possible states.
     * @param states the number of possible states.
     */
    public Field(final int states) {
        this.states = states;
        superposition = new ArrayList<>();
        for (int i = 1; i <= states; i++) {
            superposition.add(i);
        }
        Collections.shuffle(superposition);
        if (states == 1) {
            state = 1;
        }
        else {
            state = 0;
        }
    }

    /**
     * Calculate the Shannon entropy of the field given a set of weights.
     * @param wave the set of weights.
     * @return the entropy.
     */
    public double entropy(Wave wave) {
        if (superposition.size() < 2) {
            return 9999;
        }
        double entropy = 0;
        for (int i = 0; i < states; i++) {
            if (!superposition.contains(i + 1)) {
                continue;
            }
            double w = wave.getWeight(i);
            entropy += -w * Math.log(w);
        }
        return entropy / wave.getSum();
    }

    /**
     * Force the superposition to collapse down to a single state.
     */
    public void collapse() {
        while (superposition.size() > 1) {
            superposition.remove(0);
        }
        handleState();
    }

    /**
     * Remove all but the provided states from the superposition.
     * @param possible the states to keep.
     */
    public void keep(List<Integer> possible) {
        superposition.retainAll(possible);
        handleState();
    }

    /**
     * Should be invoked when the number of possible states changes.
     * Ensures the state method will return a state when the superposition has collapsed.
     */
    private void handleState() {
        if (superposition.size() == 1) {
            state = superposition.get(0);
        }
        else {
            state = 0;
        }
    }

    /**
     * Get the collapsed state, or 0 if it has not collapsed.
     * @return the state.
     */
    public int state() {
        return state;
    }
}
