package rasterize;

import model.Point;
import model.Polygon;

public class PolygonRasterizer {
    private final LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    // metoda pro vykreslení celého polygonu zadanou barvou
    public void rasterize(Polygon polygon, int color) {
        if (polygon.getSize() < 2) return; // K nakreslení alespoň jedné hrany potřebujeme minimálně 2 body

        // Procházení všech vrcholů a spojování sousedních bodů
        for (int i = 0; i < polygon.getSize() - 1; i++) {
            Point p1 = polygon.getPoint(i);
            Point p2 = polygon.getPoint(i + 1);
            lineRasterizer.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY(), color);
        }

        // Uzavření polygonu - spojení posledního vrcholu s prvním
        if (polygon.getSize() >= 3) {
            Point first = polygon.getPoint(0);
            Point last = polygon.getPoint(polygon.getSize() - 1);
            lineRasterizer.drawLine(last.getX(), last.getY(), first.getX(), first.getY(), color);
        }
    }
}
