package org.amoseman.wavefunctioncollapse.model;

import java.util.ArrayList;
import java.util.List;

public class Rule {
    private static final int DIRECTIONS = 4;
    private final int states;
    private final boolean[][][] encoded;

    public Rule(int states) {
        this.states = states;
        this.encoded = new boolean[states][states][DIRECTIONS];
    }

    public void set(int target, int neighbor, int direction) {
        encoded[target - 1][neighbor - 1][direction] = true;
    }

    public List<Integer> possible(int target, int direction) {
        List<Integer> possible = new ArrayList<>();
        for (int state = 0; state < states; state++) {
            if (encoded[target - 1][state][direction]) {
                possible.add(state + 1);
            }
        }
        return possible;
    }
}
