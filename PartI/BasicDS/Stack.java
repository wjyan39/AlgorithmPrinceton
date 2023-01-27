package PartI.BasicDS;

import java.util.Iterator;
import java.util.NoSuchElementException; 

// generic stack impelementation, supporting iterator, i.e. for-each loop.
public class Stack<MyItem> implements Iterable<MyItem> {
    
    private Node<MyItem> first;  // top of the stack 
    private int n;             // size of stack

    private static class Node<MyItem> {
        // inner node class, Linked List style
        private MyItem item;
        private Node<MyItem> next; 
    }

    public Stack() {
        first = null;
        n = 0;
    }

    /*
     * Return true if stack is empty.
     */
    public boolean isEmpty() {
        return first.equals(null);
    }

    /*
     * Return number of items in this stack.
     */
    public int size() {
        return n;
    } 

    public void push(MyItem item) {
        Node<MyItem> oldfirst = first;
        first = new Node<MyItem>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public MyItem pop() {
        MyItem item = first.item;
        first = first.next;
        n --;
        return item;
    }

    public MyItem peek() {
        return first.item; 
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (MyItem item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    private class ListIterator<MyItem> implements Iterator<MyItem> {
        private Node<MyItem> current; 

        public ListIterator(Node<MyItem> first){
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public MyItem next() {
            if (!hasNext()) throw new NoSuchElementException(); 
            MyItem item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<MyItem> iterator() {
        return new ListIterator<MyItem>(first);
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                stack.push(item);
            }
            else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
            System.println("(" + stack.size() + " left on stack.");
        }
    }

}