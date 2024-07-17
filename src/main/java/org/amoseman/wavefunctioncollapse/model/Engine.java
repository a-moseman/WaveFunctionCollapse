package org.amoseman.wavefunctioncollapse.model;

import org.amoseman.wavefunctioncollapse.view.Window;

import java.awt.image.BufferedImage;
import java.util.Optional;

public class Engine {
    private Model model;
    private Window window;
    private int steps;
    private int frames;

    public Engine setModel(Model model) {
        this.model = model;
        return this;
    }

    public Engine setWindow(Window window) {
        this.window = window;
        return this;
    }

    public Engine setSteps(int steps) {
        this.steps = steps;
        return this;
    }

    public Engine setFrames(int frames) {
        this.frames = frames;
        return this;
    }

    public Optional<BufferedImage[]> run() {
        int tick = 0;
        if (frames == 0) {
            model.init();
            while (tick < steps) {
                model.step();
                tick++;
            }
            return Optional.empty();
        }
        if (steps % frames != 0) {
            throw new RuntimeException("Steps not divisible by frames");
        }
        final int sections = steps / frames;
        int frame = 0;
        BufferedImage[] states = new BufferedImage[frames];
        model.init();
        while (tick < steps) {
            model.step();
            if (tick % sections == 0) {
                states[frame] = window.getScreen();
                frame++;
            }
            tick++;
        }
        window.close();
        return Optional.of(states);
    }
}
