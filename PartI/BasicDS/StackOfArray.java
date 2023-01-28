import java.util.Iterator;

public class StackOfArray <Item> implements Iterable<Item>{
    private int N=0; // top index of the stack in ArrayList
    private int curCap=0;
    private Item [] s; 

    public StackOfArray(int capacity) {
        // casting 
        s = (Item []) new Object[capacity];
        curCap = capacity;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (N == curCap) {
            resize(2 * curCap);
        }
        s[N++] = item;
    }

    public Item pop() {
        Item item = s[--N];
        if (N > 0 && N == curCap / 4) {
            resize(curCap / 2);
        } else {
            s[N] = null;
        }
        return item;
    }

    public int size() {
        return N;
    }

    public void resize(int capacity) { 
        Item [] copy = (Item []) new Object[capacity];
        curCap = capacity;
        for (int i=0; i < N; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

    public Item peek() {
        return s[N];
    }

    


}
