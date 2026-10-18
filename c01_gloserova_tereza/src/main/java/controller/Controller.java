package controller;

import model.Line;
import model.Point;
import model.Polygon;
import raster.RasterBufferedImage;
import rasterize.LineRasterizer;
import rasterize.LineRasterizerBresenham;
import rasterize.PolygonRasterizer;
import view.Panel;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    // komponenty pro vykreslování
    private final Panel panel;
    private final RasterBufferedImage raster;
    private final LineRasterizer lineRasterizer;
    private final PolygonRasterizer polygonRasterizer;

    // Režimy aplikace
    private enum Mode { LINE, POLYGON }
    private Mode currentMode = Mode.LINE;

    private final List<Line> lines = new ArrayList<>();
    private final Polygon polygon = new Polygon();

    private Point startPoint = null;
    private Point currentPoint = null;

    public Controller(Panel panel) {
        this.panel = panel;
        this.raster = new RasterBufferedImage(Panel.WIDTH, Panel.HEIGHT);
        this.lineRasterizer = new LineRasterizerBresenham(raster);
        this.polygonRasterizer = new PolygonRasterizer(lineRasterizer);
        this.panel.setImage(raster.getImage());
        this.panel.setHintMode("ÚSEČKA");

        initListeners();
        redraw();
    }

    // Akce uživatele
    private void initListeners() {

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (currentMode == Mode.LINE) {
                    startPoint = new Point(e.getX(), e.getY());
                    currentPoint = startPoint;
                } else if (currentMode == Mode.POLYGON) {
                    currentPoint = new Point(e.getX(), e.getY()); // Pamatuju si 1. bod polygonu
                }
                redraw(); // Okamžitě překreslíme (např. aby se objevil první bod)
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentPoint = new Point(e.getX(), e.getY());

                if (currentMode == Mode.LINE) {
                    if (startPoint != null) {
                        lines.add(new Line(startPoint, currentPoint, 0x55FF55)); // zelená barva
                    }
                } else if (currentMode == Mode.POLYGON) {
                    polygon.addPoint(currentPoint);
                }

                startPoint = null;
                currentPoint = null;

                redraw();
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentPoint = new Point(e.getX(), e.getY());
                redraw();
            }
        });

        // Klávesnice
        panel.setFocusable(true);
        panel.requestFocusInWindow(); // nutné pro reakci na klávesnici
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_C:
                        lines.clear();
                        polygon.clear();
                        startPoint = null;
                        currentPoint = null;
                        break;
                    case KeyEvent.VK_L:
                        currentMode = Mode.LINE;
                        panel.setHintMode("ÚSEČKA");
                        break;
                    case KeyEvent.VK_P:
                        currentMode = Mode.POLYGON;
                        panel.setHintMode("POLYGON");
                        break;
                }
                redraw();
            }
        });
    }

    private void redraw() {
        raster.clear(0x2B2B2B); // tmavě šedá barva

        // znovu vykreslíme
        for (Line line : lines) {
            lineRasterizer.drawLine(line);
        }
        polygonRasterizer.rasterize(polygon, 0x00BFFF); // světle modrá

        if (currentPoint != null) {
            if (currentMode == Mode.LINE && startPoint != null) {
                lineRasterizer.drawLine(startPoint.getX(), startPoint.getY(), currentPoint.getX(), currentPoint.getY(), 0xFF5555);
            } else if (currentMode == Mode.POLYGON) {
                int size = polygon.getSize();
                if (size == 0) {
                    raster.setPixel(currentPoint.getX(), currentPoint.getY(), 0xFF5555);
                } else if (size == 1) {
                    Point first = polygon.getPoint(0);
                    lineRasterizer.drawLine(first.getX(), first.getY(), currentPoint.getX(), currentPoint.getY(), 0xFF5555);
                } else {
                    Point first = polygon.getPoint(0);
                    Point last = polygon.getPoint(size - 1);
                    lineRasterizer.drawLine(first.getX(), first.getY(), currentPoint.getX(), currentPoint.getY(), 0xFF5555);
                    lineRasterizer.drawLine(last.getX(), last.getY(), currentPoint.getX(), currentPoint.getY(), 0xFF5555);
                }
            }
        }

        panel.repaint();
    }
}