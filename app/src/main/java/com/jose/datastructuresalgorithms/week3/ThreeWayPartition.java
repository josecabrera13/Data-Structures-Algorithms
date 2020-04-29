package com.jose.datastructuresalgorithms.week3;

import java.util.ArrayList;
import java.util.List;

public class ThreeWayPartition {
    private ThreeWayPartition() {

    }

    private static List<Integer> decimalDominants = new ArrayList<>();
    private static int decimalDominantValue;

    public static void sort(Integer[] array) {
        decimalDominantValue = array.length / 10;
        sort(array, 0, array.length - 1);
    }

    public static List getDecimalDominant() {
        return decimalDominants;
    }

    //SORT USING NORMAL PARTITION
    /*private static void sort(Integer[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int j = partition(array, low, high);
        sort(array, low, j - 1);
        sort(array, j + 1, high);
    }*/

    // 3 way partition
    private static void sort(Integer[] array, int low, int high) {
        // during
        // \ ----- \ -----\-----  \ ----- \
        //     v<  lt  v  i      gt  v>

        if (high <= low) {
            return;
        }
        int pivot = array[low];
        int i = low + 1, lt = low;
        int gt = high;
        while (i <= gt) {
            if (pivot < array[i]) {
                swap(array, i++, lt++);
            } else if (pivot > array[i]) {
                swap(array, i, gt--);
            } else {
                i++;
            }
        }
        if ((gt - lt) + 1 > decimalDominantValue) {
            decimalDominants.add(array[lt]);
        }
        sort(array, low, lt - 1);
        sort(array, gt + 1, high);

    }

    //Normal partition
    private static int partition(Integer[] array, int low, int high) {
        int pivot = array[low], i = 0, j = high + 1;
        while (true) {
            //scan left
            while (array[++i] < pivot) {
                if (i == high) {
                    break;
                }
            }
            //scan right
            while (array[--j] > pivot) {
                if (j == low) {
                    break;
                }
            }

            if (i >= j) {
                break;
            }
            swap(array, i, j);


        }
        swap(array, low, j);
        return j;
    }

    private static void swap(Integer[] array, int i, int j) {
        int item = array[i];
        array[i] = array[j];
        array[j] = item;
    }
}
