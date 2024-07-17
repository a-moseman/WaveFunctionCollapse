package org.amoseman.wavefunctioncollapse.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Field {
    private final int states;
    private final List<Integer> superposition;
    private int state;

    public Field(final int states) {
        this.states = states;
        superposition = new ArrayList<>();
        for (int i = 1; i <= states; i++) {
            superposition.add(i);
        }
        Collections.shuffle(superposition);
        state = 0;
    }

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

    public void collapse() {
        while (superposition.size() > 1) {
            superposition.remove(0);
        }
        handleState();
    }

    public void keep(List<Integer> possible) {
        superposition.retainAll(possible);
        handleState();
    }

    private void handleState() {
        if (superposition.size() == 1) {
            state = superposition.get(0);
        }
        else {
            state = 0;
        }
    }

    public int state() {
        return state;
    }
}
