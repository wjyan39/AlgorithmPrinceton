package PartI.BasicDS;

import java.util.NoSuchElementException;

public class RedBlackBST <Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false; 
    
    private Node root;

    // Node with color, RED refers to a red link from parent node. 
    private class Node {
        private Key key;
        private Value val;
        private Node left, right; 
        private boolean color;
        private int size; 

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    public RedBlackBST() {
    }

    // Standard Binary Search Tree APIs
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size() {
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    // Red Black BST helper functions

    private boolean isRed(Node x) {
        if (x == null) 
            return false;
        return x.color == RED;
    }

    // left rotation, when a right-leaning red link is detected.
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h; 
        x.color = h.color;
        h.color = RED;
        return x;
    }

    // right rotation, to create a temporarily right-leaning red link
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h; 
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = !h.color; 
        h.left.color = !h.left.color; 
        h.right.color = !h.right.color;
    }

    private Node moveRedLeft(Node h) {
        flipColors(h);
        if(isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        if(isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private Node balance(Node h) {
        if (isRed(h.right) && !isRed(h.left))       h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))    h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))        flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public int height() {
        return height(root);
    }
    
    private int height(Node x) {
        if (x == null) return -1;
        return 1 + Math.max(height(x.left), height(x.right));
    }


    // insertion 
    public void put(Key key, Value val) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            delete(key);
            return;
        }
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        // insert 
        if (h == null) 
            return new Node(key, val, RED, 1);
        int cmp = key.compareTo(h.key);
        if (cmp < 0)        h.left = put(h.left, key, val);
        else if (cmp > 0)   h.right = put(h.right, key, val);
        else                h.val = val;
        // reverse RB-BST structure via fix-up right-leaning links
        if (isRed(h.right) && !isRed(h.left))       h = rotateLeft(h); 
        if (isRed(h.left) && isRed(h.left.left))    h = rotateRight(h); 
        if (isRed(h.left) && isRed(h.right))        flipColors(h); 
        return h;
    }    

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = deleteMin(root);
        if(!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h) {
        if (h.left == null) 
            return null;
        if(!isRed(h.left) && !isRed(h.left.left)) 
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMax() {
        if(isEmpty()) throw new NoSuchElementException("BST underflow");
        if(!isRed(root.left) && !isRed(root.right)) 
            root.color = RED;
        root = deleteMax(root);
        if(!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node h) {
        if (!isRed(h.left))
            h = rotateRight(h);
        if (h.right == null)
            return null;
        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;
        root = delete(root, key);
        if (!isEmpty())
            root.color = BLACK;
    }

    private Node delete(Node h, Key key) {
        if (key.compareTo(h.key) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && h.right == null) 
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            } else {
                h.right = delete(h.right, key);
            }
        }
        return balance(h);
    }

    // ordered symbol table APIs
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        else return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null) return x;
        else return max(x.right);
    }

}
