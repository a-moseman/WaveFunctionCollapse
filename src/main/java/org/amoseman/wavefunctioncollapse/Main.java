package org.amoseman.wavefunctioncollapse;

import org.amoseman.wavefunctioncollapse.view.Window;

import java.awt.*;

public class Main {
    private static final int SCREEN_WIDTH = 1600;
    private static final int SCREEN_HEIGHT = 900;
    private static final int CELL_SIZE = 4;
    private static final int GRID_WIDTH = SCREEN_WIDTH / CELL_SIZE;
    private static final int GRID_HEIGHT = SCREEN_HEIGHT / CELL_SIZE;
    public static void main(String[] args) {
        Window window = new Window(SCREEN_WIDTH, SCREEN_HEIGHT);

        final int STATES = 7;
        final int MOUNTAIN = 1;
        final int BEACH = 2;
        final int SEA = 3;
        final int DEEP_SEA = 4;
        final int PLAINS = 5;
        final int FOREST = 6;
        final int DESERT = 7;

        final Color[] palette = new Color[STATES + 1];
        palette[0] = Color.BLACK;
        palette[MOUNTAIN] = Color.GRAY;
        palette[BEACH] = Color.YELLOW;
        palette[SEA] = Color.BLUE;
        palette[DEEP_SEA] = new Color(0x0000BE);
        palette[PLAINS] = new Color(0x00FF00);
        palette[FOREST] = new Color(0x00BE00);
        palette[DESERT] = new Color(0xFFA31F);

        Model model = new Model(GRID_WIDTH, GRID_HEIGHT, STATES, 1);
        for (int i = 0; i < 4; i++) {
            model.addRule(MOUNTAIN, MOUNTAIN, i);
            model.addRule(MOUNTAIN, PLAINS, i);

            model.addRule(PLAINS, PLAINS, i);
            model.addRule(PLAINS, MOUNTAIN, i);
            model.addRule(PLAINS, BEACH, i);
            model.addRule(PLAINS, FOREST, i);
            model.addRule(PLAINS, DESERT, i);

            model.addRule(FOREST, FOREST, i);
            model.addRule(FOREST, PLAINS, i);

            model.addRule(BEACH, BEACH, i);
            model.addRule(BEACH, PLAINS, i);
            model.addRule(BEACH, SEA, i);
            model.addRule(BEACH, DESERT, i);

            model.addRule(SEA, SEA, i);
            model.addRule(SEA, BEACH, i);
            model.addRule(SEA, DEEP_SEA, i);

            model.addRule(DEEP_SEA, DEEP_SEA, i);
            model.addRule(DEEP_SEA, SEA, i);

            model.addRule(DESERT, DESERT, i);
            model.addRule(DESERT, BEACH, i);
            model.addRule(DESERT, PLAINS, i);
        }
        model.setWindow(window, CELL_SIZE, palette);


        model.init();
        while (true) {
            model.step();
        }
    }
}
