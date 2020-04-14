package com.jose.datastructuresalgorithms.week3.quiz.collinearPoints;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {
    private Point[] points;
    private List<LineSegment> segments = new ArrayList<>();
    private LineSegment[] arraySegments = null;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        this.points = points.clone();
        runValidations();
    }

    private void runValidations() {
        isAnyPointNull();
        isRepeatedPointFound();
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        if (arraySegments != null) {
            return arraySegments.clone();
        }
        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    if (points[i].slopeTo(points[j]) != points[i].slopeTo(points[k])) {
                        continue;
                    }
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            segments.add(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
        }
        arraySegments = segments.toArray(new LineSegment[numberOfSegments()]);
        return arraySegments;
    }

    private void isAnyPointNull() {
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void isRepeatedPointFound() {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].slopeTo(points[i + 1]) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
        }
    }
}
