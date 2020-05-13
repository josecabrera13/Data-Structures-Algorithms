package com.jose.datastructuresalgorithms.week5.quizz;

import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class PointSET {
    private final TreeSet<Point2D> treeSet;

    public PointSET() {
        treeSet = new TreeSet<>();
    }

    public boolean isEmpty() {
        return treeSet.isEmpty();
    }

    public int size() {
        return treeSet.size();
    }

    public void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        treeSet.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        return treeSet.contains(p);
    }

    public void draw() {
        for (Point2D point : treeSet) {
            point.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        List<Point2D> pointsInRect = new LinkedList<>();
        for (Point2D point : treeSet) {
            if (rect.contains(point)) {
                pointsInRect.add(point);
            }
        }
        return pointsInRect;
    }

    public Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        double distance = Double.MAX_VALUE;
        double currentDistance;
        Point2D nearestPoint = null;
        for (Point2D point : treeSet) {
            currentDistance = point.distanceTo(p);
            if (currentDistance < distance) {
                distance = currentDistance;
                nearestPoint = point;
            }
        }
        return nearestPoint;
    }
}
