package com.jose.datastructuresalgorithms.week5.quizz;

import java.util.LinkedList;
import java.util.List;

public class KdTree {

    private Node root;
    private Point2D nearestPoint;
    private int size = 0;


    private static class Node {
        static final boolean VERTICAL = true;
        Point2D point;
        RectHV rect;
        Node left, right;
        boolean coordinate;

        Node(Point2D point, boolean coordinate, Node parent) {
            this.point = point;
            this.coordinate = coordinate;

            if (parent == null) {
                rect = new RectHV(0, 0, 1, 1);
            } else {
                double xMin = parent.rect.xmin();
                double xMax = parent.rect.xmax();
                double yMin = parent.rect.ymin();
                double yMax = parent.rect.ymax();
                int compareResult = parent.compare(this.point);
                if (parent.isVertical()) {
                    if (compareResult < 0) {
                        xMin = parent.point.x();
                    } else {
                        xMax = parent.point.x();
                    }
                } else {
                    if (compareResult < 0) {
                        yMin = parent.point.y();
                    } else {
                        yMax = parent.point.y();
                    }
                }
                rect = new RectHV(xMin, yMin, xMax, yMax);
            }
        }

        int compare(Point2D point) {
            if (isVertical()) {
                return Double.compare(this.point.x(), point.x());
            } else {
                return Double.compare(this.point.y(), point.y());
            }
        }

        boolean isVertical() {
            return this.coordinate;
        }

    }

    public KdTree() {
        //nothing
    }

    public boolean isEmpty() {
        return root == null;
    }

    public int size() {
        return size;
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        root = insert(root, p, Node.VERTICAL, null);
    }

    private Node insert(Node node, Point2D point, boolean coordinateComparison, Node parent) {
        if (node == null) {
            size++;
            return new Node(point, coordinateComparison, parent);
        }
        if (node.point.compareTo(point) == 0) {
            return node;
        }
        int compareResult = node.compare(point);
        if (compareResult < 0) {
            node.right = insert(node.right, point, !node.isVertical(), node);
        } else {
            node.left = insert(node.left, point, !node.isVertical(), node);
        }
        return node;
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return search(root, p);
    }

    private boolean search(Node node, Point2D point) {
        int resultCompare;
        while (node != null) {
            if (node.point.compareTo(point) == 0) {
                return true;
            } else {
                resultCompare = node.compare(point);
                if (resultCompare < 0) {
                    node = node.right;
                } else {
                    node = node.left;
                }
            }
        }
        return false;

    }

    public void draw() {
        draw(root);
    }

    private void draw(Node node) {
        if (node == null) {
            return;
        }
        node.point.draw();
        node.rect.draw();
        draw(node.right);
        draw(node.left);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        List<Point2D> rangePoints = new LinkedList<>();
        range(root, rect, rangePoints);
        return rangePoints;
    }

    private void range(Node node, RectHV rect, List<Point2D> rangePoints) {
        if (node == null) {
            return;
        }
        if (rect.intersects(node.rect)) {
            if (rect.contains(node.point)) {
                rangePoints.add(node.point);
            }
            range(node.left, rect, rangePoints);
            range(node.right, rect, rangePoints);
        }
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if(isEmpty()){
            return null;
        }
        nearestPoint = root.point;
        nearest(root, root.point);
        return nearestPoint;
    }

    private void nearest(Node node, Point2D point) {
        if (node == null) return;

        if (node.rect.distanceSquaredTo(point) < nearestPoint.distanceSquaredTo(point)) {
            updateNearestPoint(point, node.point);

            if (node.compare(point) > 0) {
                nearest(node.left, point);
                nearest(node.right, point);
            } else {
                nearest(node.right, point);
                nearest(node.left, point);
            }
        }
    }

    private void updateNearestPoint(Point2D queryPoint, Point2D pointCandidate) {
        if (nearestPoint == null) {
            nearestPoint = pointCandidate;
        }
        if (queryPoint.distanceTo(nearestPoint) > queryPoint.distanceTo(pointCandidate)) {
            nearestPoint = pointCandidate;
        }
    }

}
