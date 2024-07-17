package org.amoseman.wavefunctioncollapse.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void test() {
        for (int fold = 0; fold < 10; fold++) {
            Field field = new Field(4);
            List<Integer> possible = new ArrayList<>(List.of(1, 2, 3, 4));
            Collections.shuffle(possible);
            while (possible.size() > 1) {
                possible.remove(0);
            }
            int actual = possible.get(0);
            field.keep(possible);
            assertEquals(actual, field.state());

            field = new Field(1);
            assertEquals(1, field.state());
        }
    }
}