import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueOfLinkedList<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> tail;
    private int n;

    private class Node<Item> {
        private Item item;
        private Node<Item> next;
        
        public Node(Item item) {
            this.item = item;
            this.next = null;
        }
    }
    
    public QueueOfLinkedList() {
        head = new Node();
        tail = new Node();
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void enqueue(Item item) {
        if (isEmpty()) {
            head.item = item;
            tail = head;
        } else {
            tmp = new Node(item);
            tail.next = tmp;
            tail = tail.next;
        }
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw NoSuchElementException;
        
        Item ret = head.item;
        head = head.next;
        n--;
        
        if (isEmpty()) tail = head;
        
        return ret;
    }

    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current; 

        public ListIterator(Node<MyItem> head){
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
        return new ListIterator<Item>(this.head);
    }


}