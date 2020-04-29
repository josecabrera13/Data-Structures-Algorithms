package com.jose.datastructuresalgorithms.week3;

public class QuickSort {

    private QuickSort() {

    }

    public static <Item extends Comparable<Item>> void sort(Item[] a) {
        sort(a, 0, a.length - 1);
    }

    private static <Item extends Comparable<Item>> int partition(Item[] a, int low, int high) {
        int i = low;
        int j = high + 1;
        while (true) {
            while (a[++i].compareTo(a[low]) < 0) {
                if (i == high) {
                    break;
                }
            }
            while (a[low].compareTo(a[--j]) < 0) {
                if (j == low) {
                    break;
                }
            }
            if (i >= j) {
                break;
            }
            swapContent(a, i, j);
        }
        swapContent(a, low, j);
        return j;
    }

    private static <Item extends Comparable<Item>> void swapContent(Item[] a, int i, int j) {
        Item item = a[i];
        a[i] = a[j];
        a[j] = item;
    }

    private static <Item extends Comparable<Item>> Item quickSelect(Item[] a, int k) {
        int low = 0, high = a.length - 1;
        while (high > low) {
            int j = partition(a, low, high);
            if (k < j) {
                high = j - 1;
            } else if (k > j) {
                low = j + 1;
            } else {
                return a[k];
            }
        }
        return a[k];
    }

    private static <Item extends Comparable<Item>> void sort(Item[] a, int low, int high) {
        if (low >= high) {
            return;
        }
        int j = partition(a, low, high);
        sort(a, low, j - 1);
        sort(a, j + 1, high);
    }
}
