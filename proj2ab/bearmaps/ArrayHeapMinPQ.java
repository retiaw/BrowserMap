package bearmaps;

import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    class Node {
        double priority;
        T value;

        private Node(double priority, T value) {
            this.priority = priority;
            this.value = value;
        }
    }

    // pq[0] = null;
    // node * 2 = leftChild;
    // node * 2 + 1 = rightChild;
    // node / 2 = parent;
    // left first;
    ArrayList<Node> pq;
    private int size;
    private HashMap<T, Integer> table;

    private void swim(int curr) {
        while (curr != 1 && pq.get(curr).priority < pq.get(curr / 2).priority) {
            Collections.swap(pq, curr, curr / 2);
            table.put(pq.get(curr).value, curr);
            table.put(pq.get(curr / 2).value, curr / 2);
            curr = curr / 2;
        }
    }

    private void sink(int curr) {
        while (curr * 2 <= size) {
            int leftChild = curr * 2;
            int rightChild = curr * 2 + 1;
            int minNode = ( rightChild > size || pq.get(leftChild).priority < pq.get(rightChild).priority) ? leftChild : rightChild;
            if (pq.get(minNode).priority >= pq.get(curr).priority) {
                break;
            }
            Collections.swap(pq, curr, minNode);
            table.put(pq.get(curr).value, curr);
            table.put(pq.get(minNode).value, minNode);
            curr = minNode;
        }
    }

    public ArrayHeapMinPQ() {
        pq = new ArrayList<>();
        pq.add(new Node(0, null));
        size = 0;
        table = new HashMap<>();
    }

    @Override
    public void add(T item, double priority) {
        if(contains(item)) {
            throw new IllegalArgumentException("element exists");
        }
        Node newNode = new Node(priority, item);
        pq.add(newNode);
        size++;
        table.put(item, size);
        swim(size);
    }

    @Override
    public boolean contains(T item) {
        return table.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("queue is empty");
        }
        return pq.get(1).value;
    }

    @Override
    public T removeSmallest() {
        T ret = getSmallest();
        Collections.swap(pq, 1, size);
        table.put(pq.get(1).value, 1);
        pq.remove(size);
        size--;
        table.remove(ret);
        sink(1);
        return ret;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("element doesn't exist");
        }
        int index = table.get(item);
        double oldPriority = pq.get(index).priority;
        pq.get(index).priority = priority;
        if (priority < oldPriority) {
            swim(index);
        } else {
            sink(index);
        }
    }
}
