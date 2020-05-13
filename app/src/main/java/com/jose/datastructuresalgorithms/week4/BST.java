package com.jose.datastructuresalgorithms.week4;

public class BST<Key extends Comparable<Key>, Value> {

    private Node root = null;

    private class Node {
        private final Key key;
        private Value value;
        private Node left;
        private Node right;
        public int count = 0;

        private Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    public Value get(Key key) {
        Node copyRoot = root;
        while (copyRoot != null) {
            int compareResult = root.key.compareTo(key);
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

    public Key findMin() {
        Node copyRoot = root;
        while (copyRoot.left != null) {
            copyRoot = copyRoot.left;
        }
        return copyRoot.key;
    }

    public Key findMax() {
        Node copyRoot = root;
        while (copyRoot.right != null) {
            copyRoot = copyRoot.right;
        }
        return copyRoot.key;
    }

    public Key ceiling(Key key) {
        Node node = ceiling(root, key);
        if (node != null) {
            return node.key;
        }
        return null;
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) {
            return null;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult == 0) {
            return node;
        }
        if (compareResult > 0) {
            return ceiling(node.right, key);
        }
        Node searchNode = ceiling(node.left, key);
        if (searchNode == null) {
            return node;
        } else {
            return searchNode;
        }
    }

    public Key floor(Key key) {
        Node floor = floor(root, key);
        if (floor != null) {
            return floor.key;
        }
        return null;
    }

    private Node floor(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compareResult = key.compareTo(node.key);
        if (compareResult == 0) {
            return node;
        }
        if (compareResult < 0) {
            return floor(node.left, key);
        }

        Node searchGreater = floor(node.right, key);
        if (searchGreater == null) {
            return node;
        } else {
            return searchGreater;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.count;
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value);
        }
        int compareResult = node.key.compareTo(key);
        if (compareResult < 0) {
            node.left = put(node.left, key, value);
        } else if (compareResult > 0) {
            node.right = put(node.right, key, value);
        } else {
            node.value = value;
        }
        node.count = size(node.left) + size(node.right) + 1;
        return node;
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null) {
            return 0;
        }
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            return rank(node.left, key);
        } else if (compareResult > 0) {
            return size(node.left) + rank(node.right, key) + 1;
        } else {
            return size(node.left);
        }
    }

    public Node delete(Node node, Key key) {
        if (node == null) {
            return null;
        }
        //search key
        int compareResult = key.compareTo(node.key);
        if (compareResult < 0) {
            //move to the left
            node.left = delete(node.left, key);
        } else if (compareResult > 0) {
            //move to the right
            node.right = delete(node.right, key);
        } else {
            //we find the key, search if key has only one child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            //find de min, remove the min.
            Node copyNode = node;
            node = min(node.right);
            node.right = deleteMin(copyNode.right);
            node.left = copyNode.left;
        }
        return node;
    }

    public boolean isBST() {
        return isBST(root, null, null);
    }

    public boolean isBST(Node node, Key max, Key min) {
        if (node == null) {
            return true;
        }

        if (max != null && node.key.compareTo(max) >= 0) {
            return false;
        }

        if (min != null && node.key.compareTo(min) <= 0) {
            return false;
        }
        return isBST(node.right, node.key, max) && isBST(node.left, min, node.key);
    }

    private Node min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;

    }

    public Node deleteMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMin(node.left);
        node.count = size(node.left) + size(node.right) - 1;
        return node;
    }

}
