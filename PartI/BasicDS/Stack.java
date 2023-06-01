package PartI.BasicDS;

import java.util.Iterator;
import java.util.NoSuchElementException;

import Utility.StdIn;
import Utility.StdOut;

// generic stack impelementation via linked list, supporting iterator, i.e. for-each loop.
public class Stack<Item> implements Iterable<Item> {
    
    private Node<Item> first;  // top of the stack 
    private int n;             // size of stack

    private static class Node<Item> {
        // inner node class, Linked List style
        private Item item;
        private Node<Item> next; 
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

    public void push(Item item) {
        Node<Item> oldfirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }

    public Item pop() {
        Item item = first.item;
        first = first.next;
        n --;
        return item;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.item; 
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current; 

        public ListIterator(Node<Item> first){
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException(); 
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();
        while (!stack.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) {
                stack.push(item);
            }
            else if (!stack.isEmpty()) {
                StdOut.print(stack.pop() + " ");
            }
            StdOut.println("(" + stack.size() + " left on stack.");
        }
    }

}