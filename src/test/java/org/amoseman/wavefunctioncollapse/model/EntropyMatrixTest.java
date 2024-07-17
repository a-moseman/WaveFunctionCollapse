package org.amoseman.wavefunctioncollapse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntropyMatrixTest {

    @Test
    void getIndexOfLowest() {
        EntropyMatrix matrix = new EntropyMatrix(10);
        matrix.set(5, -1);
        assertEquals(5, matrix.getIndexOfLowest());
    }
}