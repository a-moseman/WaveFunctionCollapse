package org.amoseman.wavefunctioncollapse.model;

import org.amoseman.wavefunctioncollapse.view.Window;

import java.awt.*;
import java.util.List;

public class Model {
    private static final Point[] DIRECTIONS = new Point[]{new Point(0, 1), new Point(0, -1), new Point(1, 0), new Point(-1, 0)};
    private final int width;
    private final int height;
    private final int size;
    private final Field[] fields;
    private final EntropyMatrix entropyMatrix;
    private final Rule rule;
    private final Wave wave;

    private Window window;
    private int cellSize;
    private Color[] palette;

    public Model setWindow(Window window, int cellSize, Color[] palette) {
        this.window = window;
        this.cellSize = cellSize;
        this.palette = palette;
        return this;
    }

    public Model(final int width, final int height, final int states) {
        this.width = width;
        this.height = height;
        this.size = width * height;
        this.fields = new Field[size];
        for (int i = 0; i < size; i++) {
            fields[i] = new Field(states);
        }
        this.entropyMatrix = new EntropyMatrix(size);
        this.rule = new Rule(states);
        this.wave = new Wave(states);
    }

    public Model addRule(int target, int neighbor, int direction) {
        rule.set(target, neighbor, direction);
        return this;
    }

    public void init() {
        int r = (int) (Math.random() * size);
        fields[r].collapse();
        onCollapse(r);
        for (int i = 0; i < size; i++) {
            entropyMatrix.set(i, fields[i].entropy(wave));
        }
        wave.update(fields);
    }

    public void step() {
        wave.update(fields);
        final int least = entropyMatrix.getIndexOfLowest();
        fields[least].collapse();
        entropyMatrix.set(least, fields[least].entropy(wave));
        onCollapse(least);
    }

    private void onCollapse(int index) {
        final int x = index % width;
        final int y = index / width;
        Rectangle rectangle = new Rectangle(x * cellSize, y * cellSize, cellSize, cellSize);
        Color color = palette[fields[index].state()];
        window.rect(rectangle, color);
        window.update();
        for (Point d : DIRECTIONS) {
            final int x2 = x + d.x;
            final int y2 = y + d.y;
            if (outOfBounds(x2, y2)) {
                continue;
            }
            final int other = x2 + y2 * width;
            List<Integer> possible = possibleValues(fields[index].state(), x, y, x2, y2);
            fields[other].keep(possible);
            entropyMatrix.set(other, fields[other].entropy(wave));
            if (fields[other].state() > 0) {
                rectangle = new Rectangle(x2 * cellSize, y2 * cellSize, cellSize, cellSize);
                color = palette[fields[other].state()];
                window.rect(rectangle, color);
                window.update();
            }
        }
    }

    private List<Integer> possibleValues(final int target, final int x1, final int y1, final int x2, final int y2) {
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
        return rule.possible(target, direction);
    }

    private boolean outOfBounds(int x, int y) {
        return x < 0 || x >= width || y < 0 || y >= height;
    }
}
