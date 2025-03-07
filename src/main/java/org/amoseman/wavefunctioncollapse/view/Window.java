package org.amoseman.wavefunctioncollapse.view;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class Window {
    private final BufferedImage screen;
    private final Graphics graphics;
    private final JFrame frame;

    public Window(int width, int height) {
        this.screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        this.graphics = screen.getGraphics();
        this.frame = new JFrame();
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(screen, 0, 0, this);
            }
        });
        frame.setVisible(true);
    }

    public void rect(Rectangle rectangle, Color color) {
        graphics.setColor(color);
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void update() {
        frame.repaint();
    }

    public void close() {
        frame.setVisible(false);
        frame.dispose();
    }

    public BufferedImage getScreen() {
        ColorModel cm = screen.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = screen.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }
}
