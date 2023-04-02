package PartI.BasicDS;

public class RedBlackBST <Key, Value> implements Comparable<Key>{
    private static boolean RED = true;
    private static boolean BLACK = false; 
    // Node with color, RED refers to a red link from parent node. 
    private class Node {
        Key key;
        Value val;
        Node left, right; 
        boolean color; 
        Node(Key key, Value val, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }

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
        h.color = RED; 
        h.left.color = BLACK; 
        h.right.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        // insert 
        if (h == null) 
            return new Node(key, val, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) 
            h.left = put(h.left, key, val);
        else if (cmp > 0) 
            h.right = put(h.right, key, val);
        else 
            h.val = val;
        // reverse RB-BST structure 
        if (isRed(h.right) && !isRed(h.left)) 
            h = rotateLeft(h); 
        if (isRed(h.left) && isRed(h.left.left)) 
            h = rotateRight(h); 
        if (isRed(h.left) && isRed(h.right)) 
            flipColors(h); 
        return h;
    }    


}
