package com.jose.datastructuresalgorithms.week3.quiz.collinearPoints;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {
    private Point[] points;
    private List<LineSegment> lineSegments = new LinkedList<>();
    private LineSegment[] arraySegment = null;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }

        this.points = points.clone();
        runValidations();
    }

    public int numberOfSegments() {
        if (arraySegment == null) {
            return 0;
        }
        return arraySegment.length;
    }

    public LineSegment[] segments() {
        if (arraySegment != null) {
            return arraySegment.clone();
        }
        for (Point p : points) {
            Point[] slopeOrderedPoints = points.clone();
            Arrays.sort(slopeOrderedPoints, p.slopeOrder());
            double lastSlope = Double.NEGATIVE_INFINITY;
            int slopeStartIndex = 0;

            for (int j = 1; j < slopeOrderedPoints.length; j++) {
                Point q = slopeOrderedPoints[j];
                double currentSlope = p.slopeTo(q);

                if (currentSlope != lastSlope) {
                    if (j - slopeStartIndex >= 3) {
                        if (p.compareTo(slopeOrderedPoints[slopeStartIndex]) <= 0) {
                            LineSegment segment = new LineSegment(p, slopeOrderedPoints[j - 1]);
                            lineSegments.add(segment);
                        }
                    }
                    slopeStartIndex = j;
                } else if (j == slopeOrderedPoints.length - 1) {
                    if (j - slopeStartIndex >= 2) {
                        if (p.compareTo(slopeOrderedPoints[slopeStartIndex]) <= 0) {
                            LineSegment segment = new LineSegment(p, q);
                            lineSegments.add(segment);
                        }
                    }
                }

                lastSlope = currentSlope;
            }
        }
        final int segmentSize = lineSegments.size();
        arraySegment = lineSegments.toArray(new LineSegment[segmentSize]);
        return arraySegment;
    }

    private void runValidations() {
        isAnyPointNull();
        verifyDuplicatedPoints();
    }

    private void isAnyPointNull() {
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    private void verifyDuplicatedPoints() {
        Arrays.sort(points);
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i].slopeTo(points[i + 1]) == Double.NEGATIVE_INFINITY) {
                throw new IllegalArgumentException();
            }
        }
    }
}
