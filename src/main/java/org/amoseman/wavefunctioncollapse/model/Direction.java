package org.amoseman.wavefunctioncollapse.model;

import java.awt.*;

public class Direction {
    private static final Point[] DIRECTIONS = new Point[]{new Point(0, 1), new Point(0, -1), new Point(1, 0), new Point(-1, 0)};

    public int toIndex(int x, int y) {
        if (x < -1 || x > 1) {
            throw new RuntimeException("x value out of valid range");
        }
        if (y < -1 || y > 1) {
            throw new RuntimeException("y value out of valid range");
        }
        if (x == 0 && y == 0) {
            throw new RuntimeException("not a direction");
        }
        if (x == y) {
            throw new RuntimeException("x and y can not be equal");
        }
        Point point = new Point(x, y);
        for (int i = 0; i < DIRECTIONS.length; i++) {
            if (point.equals(DIRECTIONS[i])) {
                return i;
            }
        }
        throw new RuntimeException("index not found");
    }

    public Point[] list() {
        return DIRECTIONS;
    }
}
