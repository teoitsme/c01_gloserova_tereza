package raster;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RasterBufferedImage implements Raster {
    private final BufferedImage img;

    public RasterBufferedImage (int width, int height) {
        img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void setPixel(int x, int y, int color) {
        img.setRGB(x, y, color);
    }

    // získání barvy ze souřadnice, kontrola mezí
    @Override
    public int getPixel(int x, int y) {
        if (x >= 0 && x < img.getWidth() && y>= 0 && y < img.getHeight()) {
            return img.getRGB(x, y);
        }
        return 0;
    }

    @Override
    public void clear(int color) {
        Graphics g = img.getGraphics();
        g.clearRect(0, 0, img.getWidth(), img.getHeight());
    }

    @Override
    public int getWidth() {
        return img.getWidth();
    }

    @Override
    public int getHeight() {
        return img.getHeight();
    }

    public BufferedImage getImage() {
        return img;
    }
}
