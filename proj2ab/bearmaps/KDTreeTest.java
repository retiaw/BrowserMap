package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    public static void main(String[] args) {

        final int N = 100;
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            double coordinate = i * N + Math.floorMod(i * i, N);
            points.add(new Point(coordinate, coordinate));
        }

        // test nearest:
        KDTree test = new KDTree(points);
        NaivePointSet judge = new NaivePointSet(points);

        for (int i = 0; i < N; i++) {
            Point target = points.get(i);
            assertEquals(test.nearest(target.getX(), target.getY()), judge.nearest(target.getX(), target.getY()));
        }
    }
}
