package model;

import java.util.ArrayList;
import java.util.List;

public class Polygon {
    private final List<Point> points;

    public Polygon() {
        points = new ArrayList<>();
    }

    public void addPoint(Point p) {
        points.add(p);
    }

    public void clear() {
        points.clear();
    }

    public int getSize() {
        return points.size();
    }

    public Point getPoint (int index) {
        return points.get(index);
    }
}
