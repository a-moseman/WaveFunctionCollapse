package org.amoseman.wavefunctioncollapse;


import org.amoseman.wavefunctioncollapse.model.Field;
import org.amoseman.wavefunctioncollapse.view.Window;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model {
    private static final Point[] DIRECTIONS = new Point[]{new Point(0, 1), new Point(0, -1), new Point(1, 0), new Point(-1, 0)};
    private final int width;
    private final int height;
    private final int size;
    private final int states;
    private final Field[] fields;
    private final int[][][] rule;
    private final double[] wave;
    private final double waveSum;

    public Model(final int width, final int height, final int states, final double[] wave) {
        this.width = width;
        this.height = height;
        this.size = width * height;
        this.states = states;
        this.fields = new Field[size];
        for (int i = 0; i < size; i++) {
            fields[i] = new Field(states);
        }
        this.rule = new int[states][states][DIRECTIONS.length];
        this.wave = wave;
        double s = 0;
        for (double w : wave) {
            s += w;
        }
        this.waveSum = s;
    }

    public Model addRule(int target, int neighbor, int direction) {
        rule[target - 1][neighbor - 1][direction] = 1;
        return this;
    }

    public void step() {
        final double[] entropy = calculateEntropy();
        final int least = indexOfLeast(entropy);
        System.out.println(least);
        fields[least].collapse();
        onCollapse(least);
    }

    private void onCollapse(int index) {
        final int x = index % width;
        final int y = index / width;
        for (Point d : DIRECTIONS) {
            final int x2 = x + d.x;
            final int y2 = y + d.y;
            if (outOfBounds(x2, y2)) {
                continue;
            }
            final int other = x2 + y2 * width;
            List<Integer> possible = possibleValues(fields[index].state(), x, y, x2, y2);
            fields[other].keep(possible);
            //if (fields[other].state() > 0) {
            //    onCollapse(other);
            //}
        }
    }

    private List<Integer> possibleValues(final int target, final int x1, final int y1, final int x2, final int y2) {
        List<Integer> possibilities = new ArrayList<>();

        int dx = x2 - x1;
        int dy = y2 - y1;
        int direction = -1;
        for (int i = 0; i < DIRECTIONS.length; i++) {
            if (DIRECTIONS[i].equals(new Point(dx, dy))) {
                direction = i;
            }
        }
        if (direction == -1) {
            throw new RuntimeException("Failed to find direction");
        }


        for (int state = 0; state < states; state++) {
            if (rule[target - 1][state][direction] == 1) {
                possibilities.add(state + 1);
            }
        }

        return possibilities;
    }

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }

    private double[] calculateEntropy() {
        double[] entropy = new double[size];
        for (int i = 0; i < size; i++) {
            Field field = fields[i];
            entropy[i] = field.entropy(wave, waveSum);
        }
        return entropy;
    }

    private int indexOfLeast(double[] values) {
        int least = 0;
        for (int i = 1; i < values.length; i++) {
            if (values[least] > values[i]) {
                least = i;
            }
        }
        return least;
    }

    public void draw(Window window, int cellSize, Color[] palette) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Field field = fields[x + y * width];
                Color color = palette[field.state()];
                window.rect(new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize), color);
            }
        }
    }
}
