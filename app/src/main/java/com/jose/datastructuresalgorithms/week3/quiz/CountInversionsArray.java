package com.jose.datastructuresalgorithms.week3.quiz;

/**
 * I repeated the mergeSort implementation to get more familiarized with the algorithm.
 */
public class CountInversionsArray {

    private CountInversionsArray() {

    }

    private static <Item extends Comparable<Item>> int merge(Item[] array, Item[] aux, int lowIndex, int midIndex, int highIndex) {
        //Copy values into aux array
        if (highIndex + 1 - lowIndex >= 0) {
            System.arraycopy(array, lowIndex, aux, lowIndex, highIndex + 1 - lowIndex);
        }
        int inversions = 0;
        int i = lowIndex;
        int j = midIndex + 1;

        for (int k = lowIndex; k <= highIndex; k++) {
            if (i > midIndex) {
                array[k] = aux[j++];
            } else if (j > highIndex) {
                array[k] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) < 0) {
                array[k] = aux[i++];
            } else {
                inversions += j - k;
                array[k] = aux[j++];
            }
        }
        return inversions;
    }

    private static <Item extends Comparable<Item>> int sort(Item[] array, Item[] aux, int lowIndex, int highIndex) {
        if (highIndex <= lowIndex) {
            return 0;
        }
        int inversions = 0;
        int midIndex = lowIndex + ((highIndex - lowIndex) / 2);
        inversions += sort(array, aux, lowIndex, midIndex);
        inversions += sort(array, aux, midIndex + 1, highIndex);
        inversions += merge(array, aux, lowIndex, midIndex, highIndex);
        return inversions;
    }

    public static <Item extends Comparable<Item>> int countInversions(Item[] array) {
        return sort(array, array.clone(), 0, array.length - 1);
    }
}
