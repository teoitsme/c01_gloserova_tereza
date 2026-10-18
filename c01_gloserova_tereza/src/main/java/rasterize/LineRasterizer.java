package rasterize;

import model.Line;
import raster.Raster;

public abstract class LineRasterizer {
    protected Raster raster;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    // Abstraktní metoda pro samotný výpočet a kreslení, přijímá čisté souřadnice a barvu
    public abstract void drawLine(int x1, int y1, int x2, int y2, int color);

    // Přetížená pomocná metoda pro pohodlnější kreslení, rozbalí datový objekt Line a předá jeho hodnoty abstraktní metodě výše
    public void drawLine(Line line) {
        drawLine(line.getP1().getX(), line.getP1().getY(), line.getP2().getX(), line.getP2().getY(), line.getColor());
    }
}
