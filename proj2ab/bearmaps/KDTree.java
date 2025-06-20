package bearmaps;

import java.nio.channels.Pipe;
import java.util.List;

public class KDTree implements PointSet {

    private class Node {
        private Point point;

        private Node leftChild;
        private Node rightChild;

        private Node(Point point) {
            this.point = point;
        }
    }

    private Node root;

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = construct(root, new Node(p), true);
        }
    }

    private Node construct(Node node, Node newNode, boolean nextLevelIsX) {
        if (node == null) {
            return newNode;
        } else {
            if (nextLevelIsX) {
                if (Double.compare(node.point.getX(), newNode.point.getX()) < 0) {
                    node.rightChild = construct(node.rightChild, newNode, !nextLevelIsX);
                } else {
                    node.leftChild = construct(node.leftChild, newNode, !nextLevelIsX);
                }
            } else {
                if (Double.compare(node.point.getY(), newNode.point.getY()) < 0) {
                    node.rightChild = construct(node.rightChild, newNode, !nextLevelIsX);
                } else {
                    node.leftChild = construct(node.leftChild, newNode, !nextLevelIsX);
                }
            }
        }
        return node;
    }

    @Override
    public Point nearest(double x, double y) {
        Node target = new Node(new Point(x, y));
        return nearestHelper(root, target, new Node(new Point(Double.MAX_VALUE, Double.MAX_VALUE)), true).point;
    }

    private Node nearestHelper(Node currNode, Node target, Node minNode, boolean thisIsY) {
        if (currNode == null) {
            return minNode;
        } else {
            double currDistance = distance(currNode.point, target.point);
            double minDistance = distance(minNode.point, target.point);
            minNode = (Double.compare(currDistance, minDistance) < 0) ? currNode : minNode;

            double coordinateTarget = (thisIsY) ? target.point.getY() : target.point.getX();
            double coordinateCurrent = (thisIsY) ? currNode.point.getY() : currNode.point.getX();
            double delta = Math.pow(coordinateTarget - coordinateCurrent, 2);

            if (coordinateTarget < coordinateCurrent) {
                minNode = nearestHelper(currNode.leftChild, target, minNode, !thisIsY);
                minDistance = distance(minNode.point, target.point);
                if (delta < minDistance) {
                    minNode = nearestHelper(currNode.rightChild, target, minNode, !thisIsY);
                }
            } else {
                minNode = nearestHelper(currNode.rightChild, target, minNode, !thisIsY);
                minDistance = distance(minNode.point, target.point);
                if (delta < minDistance) {
                    minNode = nearestHelper(currNode.leftChild, target, minNode, !thisIsY);
                }
            }

            return minNode;
        }
    }

    private double distance(Point str, Point dst) {
        return Math.pow(str.getX() - dst.getX(), 2) + Math.pow(str.getY() - dst.getY(), 2);
    }
}
