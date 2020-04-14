package com.jose.datastructuresalgorithms.week3;

import java.util.Comparator;

public class MergeSort {

    private static <Item extends Comparable<Item>> void merge(final Item[] array,
                                                              final Item[] auxiliaryArray,
                                                              int lowIndex,
                                                              int midPoint,
                                                              int heightIndex,
                                                              final Comparator<Item> comparator) {
        for (int i = lowIndex; i <= heightIndex; i++) {
            auxiliaryArray[i] = array[i];
        }
        int i = lowIndex, j = midPoint + 1;

        for (int k = lowIndex; k <= heightIndex; k++) {
            if (i > midPoint) {
                array[k] = auxiliaryArray[j++];
            } else if (j > heightIndex) {
                array[k] = auxiliaryArray[i++];
            } else if (less(auxiliaryArray[i], auxiliaryArray[j], comparator)) {
                array[k] = auxiliaryArray[i++];
            } else {
                array[k] = auxiliaryArray[j++];
            }
        }
    }

    private static <Item extends Comparable<Item>> void sort(final Item[] array,
                                                             final Item[] auxiliaryArray,
                                                             int lowIndex,
                                                             int heightIndex,
                                                             final Comparator<Item> comparator) {
        if (heightIndex <= lowIndex) {
            return;
        }
        int midPoint = lowIndex + (heightIndex - lowIndex) / 2;
        sort(array, auxiliaryArray, lowIndex, midPoint, comparator);
        sort(array, auxiliaryArray, midPoint + 1, heightIndex, comparator);
        merge(array, auxiliaryArray, lowIndex, midPoint, heightIndex, comparator);

    }

    public static <Item extends Comparable<Item>> void sort(final Item[] array, final Comparator<Item> comparator) {
        Item[] auxiliaryArray = array.clone();
        sort(array, auxiliaryArray, 0, array.length - 1, comparator);
    }

    private static <Item extends Comparable<Item>> boolean less(Item item1, Item item2, Comparator<Item> comparator) {
        if (comparator != null) {
            return comparator.compare(item1, item2) < 0;
        }
        return item1.compareTo(item2) < 0;
    }
}
