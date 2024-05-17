package ru.ArseniiDeviatkin.lab1.task2;
import java.util.ArrayList;
import java.util.List;


class SplayTree {

    class Node {
        int key;
        Node left, right;

        Node(int key) {
            this.key = key;
            this.left = this.right = null;
        }
    }

    private Node root;

    public SplayTree() {
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public int getRootKey() {
        if (root != null) {
            return root.key;
        } else {
            throw new IllegalStateException("Tree is empty");
        }
    }

    public int getCount(int key) {
        return getCountHelper(root, key);
    }

    private int getCountHelper(Node node, int key) {
        if (node == null) {
            return 0;
        }
        int count = (node.key == key) ? 1 : 0;
        count += getCountHelper(node.left, key);
        count += getCountHelper(node.right, key);
        return count;
    }

    private Node rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        y.right = x;
        return y;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        y.left = x;
        return y;
    }

    private Node splay(Node root, int key) {
        if (root == null || root.key == key)
            return root;

        if (root.key > key) {
            if (root.left == null) return root;

            if (root.left.key > key) {
                root.left.left = splay(root.left.left, key);
                root = rightRotate(root);
            } else if (root.left.key < key) {
                root.left.right = splay(root.left.right, key);
                if (root.left.right != null)
                    root.left = leftRotate(root.left);
            }
            return (root.left == null) ? root : rightRotate(root);
        } else {
            if (root.right == null) return root;

            if (root.right.key > key) {
                root.right.left = splay(root.right.left, key);
                if (root.right.left != null)
                    root.right = rightRotate(root.right);
            } else if (root.right.key < key) {
                root.right.right = splay(root.right.right, key);
                root = leftRotate(root);
            }
            return (root.right == null) ? root : leftRotate(root);
        }
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
            return;
        }

        root = splay(root, key);

        if (root.key == key) return;

        Node newNode = new Node(key);

        if (root.key > key) {
            newNode.right = root;
            newNode.left = root.left;
            root.left = null;
        } else {
            newNode.left = root;
            newNode.right = root.right;
            root.right = null;
        }
        root = newNode;
    }

    public void delete(int key) {
        if (root == null) return;

        root = splay(root, key);

        if (root.key != key) return;

        if (root.left == null) {
            root = root.right;
        } else {
            Node temp = root;
            root = splay(root.left, key);
            root.right = temp.right;
        }
    }

    public boolean search(int key) {
        root = splay(root, key);
        return root != null && root.key == key;
    }

    public List<Integer> inOrder() {
        List<Integer> result = new ArrayList<>();
        inOrderRec(root, result);
        return result;
    }

    private void inOrderRec(Node root, List<Integer> result) {
        if (root != null) {
            inOrderRec(root.left, result);
            result.add(root.key);
            inOrderRec(root.right, result);
        }
    }
}
