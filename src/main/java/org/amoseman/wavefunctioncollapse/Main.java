package org.amoseman.wavefunctioncollapse;

import org.amoseman.wavefunctioncollapse.view.Window;

import java.awt.*;

public class Main {
    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 640;
    private static final int CELL_SIZE = 8;
    private static final int GRID_WIDTH = SCREEN_WIDTH / CELL_SIZE;
    private static final int GRID_HEIGHT = SCREEN_HEIGHT / CELL_SIZE;
    public static void main(String[] args) {
        Window window = new Window(SCREEN_WIDTH, SCREEN_HEIGHT);

        final int MOUNTAIN = 1;
        final int BEACH = 2;
        final int SEA = 3;
        final int DEEP_SEA = 4;
        final int LAND = 5;

        final Color[] palette = new Color[6];
        palette[0] = Color.BLACK;
        palette[MOUNTAIN] = Color.GRAY;
        palette[BEACH] = Color.YELLOW;
        palette[SEA] = Color.BLUE;
        palette[DEEP_SEA] = new Color(0x0000C0);
        palette[LAND] = Color.GREEN;

        Model model = new Model(GRID_WIDTH, GRID_HEIGHT, 5, 1);
        for (int i = 0; i < 4; i++) {
            model.addRule(MOUNTAIN, MOUNTAIN, i);
            model.addRule(MOUNTAIN, LAND, i);

            model.addRule(LAND, LAND, i);
            model.addRule(LAND, MOUNTAIN, i);
            model.addRule(LAND, BEACH, i);

            model.addRule(BEACH, BEACH, i);
            model.addRule(BEACH, LAND, i);
            model.addRule(BEACH, SEA, i);

            model.addRule(SEA, SEA, i);
            model.addRule(SEA, BEACH, i);

            model.addRule(DEEP_SEA, DEEP_SEA, i);
            model.addRule(DEEP_SEA, SEA, i);
        }
        model.setWindow(window, CELL_SIZE, palette);


        model.init();
        //int tick = 0;
        while (true) {
            model.step();
            /*
            if (tick % 1 == 0) {
                model.draw(window, CELL_SIZE, palette);
                tick = 0;
            }
            tick++;

             */
        }
    }
}
