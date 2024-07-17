package org.amoseman.wavefunctioncollapse.io;

import com.squareup.gifencoder.GifEncoder;
import com.squareup.gifencoder.ImageOptions;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

public class GifWriter {
    private String fileName;
    private BufferedImage[] frames;
    private long frameDelay;

    public GifWriter setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public GifWriter setFrames(BufferedImage[] frames) {
        this.frames = frames;
        return this;
    }

    public GifWriter setFrameDelay(long frameDelay) {
        this.frameDelay = frameDelay;
        return this;
    }

    public void write() {
        try {
            OutputStream stream = new FileOutputStream(fileName);
            ImageOptions options = new ImageOptions();
            options.setDelay(frameDelay, TimeUnit.MILLISECONDS);
            GifEncoder encoder = new GifEncoder(stream, frames[0].getWidth(), frames[0].getHeight(), 0);
            for (BufferedImage image : frames) {
                int[][] data = new int[image.getHeight()][image.getWidth()];
                for (int y = 0; y < image.getHeight(); y++) {
                    for (int x = 0; x < image.getWidth(); x++) {
                        data[y][x] = image.getRGB(x, y);
                    }
                }
                encoder.addImage(data, options);
            }
            encoder.finishEncoding();
            stream.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
