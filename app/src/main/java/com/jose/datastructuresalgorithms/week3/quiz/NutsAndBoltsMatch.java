package com.jose.datastructuresalgorithms.week3.quiz;

public class NutsAndBoltsMatch {

    private NutsAndBoltsMatch() {
        //Nothing
    }

    private static <Item extends Comparable<Item>> int partition(Item[] a, int low, int high, Item pivot) {
        int i = low, j = high;
        Integer pivotIndex = null;
        while (true) {
            //scan from low to j-1
            while (true) {
                if (i == high) {
                    break;
                }
                int result = a[i].compareTo(pivot);
                if (result == 0) {
                    pivotIndex = i;
                    i++;
                    continue;
                }
                if (result < 0) {
                    i++;
                } else {
                    break;
                }
            }
            //scan from j+1 to high
            while (true) {
                if (j == low) {
                    break;
                }
                int result = a[j].compareTo(pivot);
                if (result == 0) {
                    pivotIndex = i;
                    i++;
                    continue;
                }
                if (result > 0) {
                    j--;
                } else {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swapValues(a, i, j);
        }

        if (pivotIndex == null) {
            throw new IllegalArgumentException();
        }
        swapValues(a, pivotIndex, j);
        return j;
    }

    private static <Item extends Comparable<Item>> void swapValues(Item[] a, int i, int j) {
        Item item = a[i];
        a[i] = a[j];
        a[j] = item;
    }

    private static <Item extends Comparable<Item>> void matchArray(Item[] a, Item[] b, int low, int high) {
        if (low >= high) {
            return;
        }
        int j = partition(a, low, high, a[low]);
        partition(b, low, high, a[j]);

        matchArray(a, b, low, j - 1);
        matchArray(a, b, j + 1, high);
    }

    public static <Item extends Comparable<Item>> void matchArray(Item[] a, Item[] b) {
        matchArray(a, b, 0, a.length - 1);
    }


}
