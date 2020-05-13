package com.jose.datastructuresalgorithms.week5;

public class LLRBT<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private static final boolean RED = true;
        private static final boolean BLACK = false;
        final Key key;
        Value value;
        Node left;
        Node right;
        boolean color;

        private Node(Key key, Value value, boolean color) {
            this.key = key;
            this.value = value;
        }
    }

    public Value search(Key key) {
        Node copyRoot = root;
        while (copyRoot != null) {
            int compareResult = key.compareTo(copyRoot.key);
            if (compareResult < 0) {
                copyRoot = copyRoot.left;
            } else if (compareResult > 0) {
                copyRoot = copyRoot.right;
            } else {
                return copyRoot.value;
            }
        }
        return null;
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return new Node(key, value, Node.RED);
        }
        int compareResult = x.key.compareTo(key);
        if (compareResult < 0) {
            x.left = put(x.left, key, value);
        } else if (compareResult > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        if (isRed(x.right) && !isRed(x.left)) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColor(x);
        }

        return x;
    }

    private Node rotateLeft(Node node) {
        Node x = node.right;
        x.left = node;
        node.right = x.left;
        x.color = node.color;
        node.color = Node.RED;
        return x;
    }

    private Node rotateRight(Node node) {
        Node x = node.left;
        x.right = node;
        node.left = x.right;
        x.color = node.color;
        node.color = Node.RED;
        return x;
    }

    private void flipColor(Node node) {
        node.color = Node.RED;
        node.left.color = Node.BLACK;
        node.right.color = Node.BLACK;
    }

    private boolean isRed(Node x) {
        return x != null && x.color;
    }
}
