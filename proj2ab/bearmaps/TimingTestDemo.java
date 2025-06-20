package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug. Demonstrates how you can use either
 * System.currentTimeMillis or the Princeton Stopwatch
 * class to time code.
 */
public class TimingTestDemo {
    public static void main(String[] args) {

        final long N = 10000000;
        ArrayHeapMinPQ<Integer> test = new ArrayHeapMinPQ<>();

        // add:
        long start = System.currentTimeMillis();
        for (int i = 0; i < N; i += 1) {
            test.add(i, i);
        }
        long end = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        // changePriority:
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < N; i += 1) {
            test.changePriority(i, -i);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Total time elapsed: " + (end1 - start1)/1000.0 +  " seconds.");

        // remove:
        Stopwatch sw = new Stopwatch();
        for (int i = 0; i < N; i += 1) {
            test.removeSmallest();
        }
        System.out.println("Total time elapsed: " + sw.elapsedTime() +  " seconds.");

    }
}
