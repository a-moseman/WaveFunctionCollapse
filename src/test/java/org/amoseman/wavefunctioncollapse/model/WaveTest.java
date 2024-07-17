package org.amoseman.wavefunctioncollapse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaveTest {

    @Test
    void test() {
        Field[] fields = new Field[5];
        for (int i = 0; i < fields.length; i++) {
            fields[i] = new Field(4);
            fields[i].collapse();
        }
        Wave wave = new Wave(4);
        wave.update(fields);
        double[] histogram = new double[4];
        for (Field field : fields) {
            histogram[field.state() - 1] += 1.00 / fields.length;
        }
        for (int i = 0; i < 4; i++) {
            assertEquals(histogram[i], wave.getWeight(i));
        }
    }
}