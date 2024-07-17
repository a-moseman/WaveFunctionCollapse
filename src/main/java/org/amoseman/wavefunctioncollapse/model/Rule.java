package org.amoseman.wavefunctioncollapse.model;

import java.util.ArrayList;
import java.util.List;

/**
 * One-hot encodes a set of rules a model will attempt to follow.
 */
public class Rule {
    private static final int DIRECTIONS = 4;
    private final int states;
    private final boolean[][][] encoded;

    /**
     * Instantiate a new set of rules, based on a given number of states.
     * @param states the number of states.
     */
    public Rule(int states) {
        this.states = states;
        this.encoded = new boolean[states][states][DIRECTIONS];
    }

    /**
     * Encode a new rule.
     * @param target the target cell state of the rule.
     * @param neighbor the neighbor cell state of the rule.
     * @param direction the cardinal direction from target cell to neighbor cell for the rule.
     */
    public void set(int target, int neighbor, int direction) {
        encoded[target - 1][neighbor - 1][direction] = true;
    }

    /**
     * Determine the possible states provided a target cell state and a direction.
     * @param target the target cell state.
     * @param direction the cardinal direction from target cell to neighbor cell.
     * @return the possible states.
     */
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
