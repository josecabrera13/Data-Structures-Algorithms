package com.jose.datastructuresalgorithms.week3.quiz

import com.jose.datastructuresalgorithms.week3.quiz.collinearPoints.FastCollinearPoints
import com.jose.datastructuresalgorithms.week3.quiz.collinearPoints.LineSegment
import com.jose.datastructuresalgorithms.week3.quiz.collinearPoints.Point
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FastCollinearPointsTest {
    private val pointsTestDataSet1 = mutableListOf<Point>()
    private val pointsTestDataSet2 = mutableListOf<Point>()

    @Before
    fun init() {
        pointsTestDataSet1.add(Point(10000, 0))
        pointsTestDataSet1.add(Point(0, 10000))
        pointsTestDataSet1.add(Point(3000, 7000))
        pointsTestDataSet1.add(Point(7000, 3000))
        pointsTestDataSet1.add(Point(20000, 21000))
        pointsTestDataSet1.add(Point(3000, 4000))
        pointsTestDataSet1.add(Point(14000, 15000))
        pointsTestDataSet1.add(Point(6000, 7000))

        pointsTestDataSet2.add(Point(19000, 10000))
        pointsTestDataSet2.add(Point(18000, 10000))
        pointsTestDataSet2.add(Point(32000, 10000))
        pointsTestDataSet2.add(Point(21000, 10000))
        pointsTestDataSet2.add(Point(1234, 5678))
        pointsTestDataSet2.add(Point(14000, 10000))

    }

    @Test
    fun countSegmentsDataSet1() {
        val segmentsExcepted = mutableListOf<LineSegment>()
        segmentsExcepted.add(LineSegment(Point(10000, 0), Point(0, 10000)))
        segmentsExcepted.add(LineSegment(Point(3000, 4000), Point(20000, 21000)))
        val resultSegments = FastCollinearPoints(pointsTestDataSet1.toTypedArray()).segments()
        Assert.assertArrayEquals(segmentsExcepted.toTypedArray(), resultSegments)
    }

    @Test
    fun countSegmentsDataSet2() {
        val segmentsExcepted = mutableListOf<LineSegment>()
        segmentsExcepted.add(LineSegment(Point(14000, 10000), Point(32000, 10000)))
        val resultSegments = FastCollinearPoints(pointsTestDataSet2.toTypedArray()).segments()
        Assert.assertArrayEquals(segmentsExcepted.toTypedArray(), resultSegments)
    }
}