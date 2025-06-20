package bearmaps;

import edu.princeton.cs.algs4.In;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    public void testAdd() {
        final int N = 100;
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> judge = new NaiveMinPQ<>();
        for (int i = 0; i < N; i++) {
            double priority = i * N + Math.floorMod(i * i, N);
            test.add(i, priority);
            judge.add(i, priority);
            assertEquals(test.pq.size() - 1, judge.items.size());
        }
        for (int i = 0; i < N; i++) {
            assertEquals(test.pq.get(i + 1).value, judge.items.get(i).getItem());
        }
    }

    public void testRemove() {
        final int N = 100;
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> judge = new NaiveMinPQ<>();
        for (int i = 0; i < N; i++) {
            double priority = i * N + Math.floorMod(i * i, N);
            test.add(i, priority);
            judge.add(i, priority);
            assertEquals(test.pq.size() - 1, judge.items.size());
        }
        for (int i = 0; i < N; i++) {
            assertEquals(test.removeSmallest(), judge.removeSmallest());
        }
    }

    public void testChangePriority() {
        final int N = 100;
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> judge = new NaiveMinPQ<>();
        for (int i = 0; i < N; i++) {
            double priority = i;
            test.add(i, priority);
            judge.add(i, priority);
            assertEquals(test.pq.size() - 1, judge.items.size());
        }
        for (int i = 0; i < N; i++) {
            // sink:
            test.changePriority(i, N + i);
            judge.changePriority(i , N + i);
            assertEquals(test.getSmallest(), judge.getSmallest());
            // swim:
            test.changePriority(i, -N - i);
            judge.changePriority(i, -N - i);
            assertEquals(test.getSmallest(), judge.getSmallest());
        }
    }

    public static void main(String[] args) {
        ArrayHeapMinPQTest test = new ArrayHeapMinPQTest();
        test.testAdd();
        test.testRemove();
        test.testChangePriority();
    }
}


