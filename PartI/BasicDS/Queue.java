package PartI.BasicDS;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int n;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        
        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }
    
    public Queue() {
        head = null;
        tail = null;
        n = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return n;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return head.item;
    }

    public void enqueue(Item item) {
        // ensure FIFO
        Node<Item> oldtail = tail;
        tail = new Node<Item>(item);
        if (isEmpty()) head = tail;
        else           oldtail.next = tail;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        
        Item ret = head.item;
        head = head.next;
        n--;
        
        if (isEmpty()) tail = head;
        
        return ret;
    }

    private class ListIterator implements Iterator<Item> {
        private Node<Item> current; 

        public ListIterator(Node<Item> head){
            current = head;
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
        return new ListIterator(this.head);
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item:this) {
            s.append(item);
            s.append(" ");
        }
        return s.toString();
    }

}