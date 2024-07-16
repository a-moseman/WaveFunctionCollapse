package org.amoseman.wavefunctioncollapse.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FieldTest {

    @Test
    void keep() {
        for (int fold = 0; fold < 32; fold++) {
            Field field = new Field(4);
            assertEquals(0, field.state());
            assertEquals(1.3862943611198906d, field.entropy());
            List<Integer> possible = new ArrayList<>();
            for (int i = 1; i < 5; i++) {
                possible.add(i);
            }
            Collections.shuffle(possible);
            while (possible.size() > 1) {
                possible.remove(0);
            }
            field.keep(possible);
            assertEquals(1, possible.size());
            assertEquals(possible.get(0), field.state());
        }
    }

    @Test
    void collapse() {
        for (int fold = 0; fold < 32; fold++) {
            Field field = new Field(4);
            field.collapse();
            List<Integer> possible = List.of(1, 2, 3, 4);
            assertTrue(possible.contains(field.state()));
        }
    }
}