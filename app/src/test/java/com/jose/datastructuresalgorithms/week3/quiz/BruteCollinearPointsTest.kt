package com.jose.datastructuresalgorithms.week3.quiz

import com.jose.datastructuresalgorithms.week3.quiz.collinearPoints.BruteCollinearPoints
import com.jose.datastructuresalgorithms.week3.quiz.collinearPoints.LineSegment
import com.jose.datastructuresalgorithms.week3.quiz.collinearPoints.Point
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class BruteCollinearPointsTest {
    private val pointsTestDataSet = mutableListOf<Point>()
    private val repeatedPointsDataSet = mutableListOf<Point>()

    @Before
    fun init() {
        pointsTestDataSet.add(Point(10000, 0))
        pointsTestDataSet.add(Point(0, 10000))
        pointsTestDataSet.add(Point(3000, 7000))
        pointsTestDataSet.add(Point(7000, 3000))
        pointsTestDataSet.add(Point(20000, 21000))
        pointsTestDataSet.add(Point(3000, 4000))
        pointsTestDataSet.add(Point(14000, 15000))
        pointsTestDataSet.add(Point(6000, 7000))

        repeatedPointsDataSet.add(Point(23620, 8263))
        repeatedPointsDataSet.add(Point(17291, 21630))
        repeatedPointsDataSet.add(Point(22935, 26352))
        repeatedPointsDataSet.add(Point(6484, 8096))
        repeatedPointsDataSet.add(Point(22935, 26352))
    }

    @Test
    fun countSegments() {
        val segmentsExcepted = mutableListOf<LineSegment>()
        segmentsExcepted.add(LineSegment(Point(10000, 0), Point(0, 10000)))
        segmentsExcepted.add(LineSegment(Point(3000, 4000), Point(20000, 21000)))
        val dataSetArray = pointsTestDataSet.toTypedArray()
        val collinearPointsAlgorithm = BruteCollinearPoints(dataSetArray)
        var segmentsResult = collinearPointsAlgorithm.segments()
        Assert.assertArrayEquals(segmentsExcepted.toTypedArray(), segmentsResult)
        dataSetArray[0] = Point(10000, 1)
        segmentsResult = collinearPointsAlgorithm.segments()
        Assert.assertArrayEquals(segmentsExcepted.toTypedArray(), segmentsResult)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testCheckRepeatedPoints() {
        BruteCollinearPoints(repeatedPointsDataSet.toTypedArray())
    }
}