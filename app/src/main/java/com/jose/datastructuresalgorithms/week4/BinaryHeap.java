package com.jose.datastructuresalgorithms.week4;

// Binary Heap esta basado en la idea de un arbol binario completo. Un arbol completo es aquel que esta perfectamente balanceado, excepto tal vez en su ultimo nivel.
// Una propiedad de los arboles binarios completos es que high = logaritmo base 2 (N) donde N son sus elementos.
// Propiedad de los heap -> el padre no es mas chico que los hijos.
// Vamos a usar un array para representar al arbol donde a[1] = root, a[2] y a[3] son los hijos del root y a[4],a[5] son los hijos de a[2] y asi sucesivante.
// padre de k = k/2
// hijos k = 2k y 2k+1
public class BinaryHeap<Key extends Comparable<Key>> {

    private Key[] array;
    private int n = 0;

    public BinaryHeap() {
        array = (Key[]) new Comparable[4];
    }

    public void insert(Key item) {
        if (n > 0 && n == array.length) {
            resize(array.length * 2);
        }
        array[++n] = item;
        swim(n);
    }

    public Key deleteMax() {
        Key item = array[1];
        swap(1, n);
        array[n--] = null;
        sink(1);
        if (n > 0 && n == array.length / 4) {
            resize(array.length / 2);
        }
        return item;
    }

    private void resize(int newSize) {
        Key[] newArray = (Key[]) new Comparable[newSize];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            //check which son is bigger
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {
                j++;
            }
            // check if bigger son with parent
            if (less(j, k)) {
                break;
            }
            swap(k, j);
            k = j;
        }
    }

    private void swap(int i, int j) {
        Key item = array[i];
        array[i] = array[j];
        array[j] = item;

    }

    private boolean less(int i, int j) {
        return array[i].compareTo(array[j]) < 0;
    }
}
