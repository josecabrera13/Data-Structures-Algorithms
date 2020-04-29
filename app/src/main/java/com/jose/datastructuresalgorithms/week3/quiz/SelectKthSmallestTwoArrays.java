package com.jose.datastructuresalgorithms.week3.quiz;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectKthSmallestTwoArrays {
    private SelectKthSmallestTwoArrays() {

    }

    public static int mergeSelect(Integer[] a, Integer[] b, int k) {
        return 0;
    }

    public static int quickSelect(Integer[] a, Integer[] b, int k) {

        if (a.length == 0) {
            return b[k];
        } else if (b.length == 0) {
            return a[k];
        }

        int midPointA = a.length / 2;
        int midPointB = b.length / 2;

        if (midPointA + midPointB < k) {
            // k is in the right side of the mid point
            if (a[midPointA] < b[midPointB]) {
                // remove the first part of the array a
                return quickSelect(copyArray(a, midPointA + 1, a.length - 1), b, k - midPointA - 1 );
            } else {
                //remove the first part of array b
                return quickSelect(a, copyArray(b, midPointB + 1, b.length - 1), k - midPointB - 1);
            }
        } else {
            // K is in the left side of the mid point
            if (a[midPointA] < b[midPointB]) {
                //remove the second part of array b
                return quickSelect(a, copyArray(b, 0, midPointB - 1), k);
            } else {
                //remove the second part of array a
                return quickSelect(copyArray(a, 0, midPointA - 1), b, k);
            }
        }
    }

    private static Integer[] copyArray(Integer[] array, int start, int end) {
        ArrayList<Integer> copy = new ArrayList<>(Arrays.asList(array).subList(start, end + 1));
        final int listSize = copy.size();
        return copy.toArray(new Integer[listSize]);
    }
}
