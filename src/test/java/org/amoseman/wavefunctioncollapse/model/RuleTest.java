package org.amoseman.wavefunctioncollapse.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RuleTest {

    @Test
    void possible() {
        Rule rule = new Rule(4);
        rule.set(1, 1, 0);
        rule.set(1, 2, 0);
        rule.set(1, 3, 1);
        List<Integer> possible = rule.possible(1, 0);
        assertTrue(possible.contains(1));
        assertTrue(possible.contains(2));
        assertFalse(possible.contains(3));
    }
}