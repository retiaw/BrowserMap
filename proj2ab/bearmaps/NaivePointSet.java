package bearmaps;

import java.util.List;

public class NaivePointSet implements PointSet {

    private List<Point> pointSet;

    public NaivePointSet(List<Point> points) {
        pointSet = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);

        Point nearest = pointSet.getFirst();
        double minDistance = distance(target, nearest);

        for (int i = 1; i < pointSet.size(); i++) {
            double newDistance = distance(target, pointSet.get(i));
            if (Double.compare(newDistance, minDistance) < 0) {
                nearest = pointSet.get(i);
                minDistance = newDistance;
            }
        }

        return nearest;
    }

    private double distance(Point str, Point dst) {
        return Math.pow(str.getX() - dst.getX(), 2) + Math.pow(str.getY() - dst.getY(), 2);
    }
}
