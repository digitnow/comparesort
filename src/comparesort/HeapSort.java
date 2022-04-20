/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparesort;

/**
 * Sorts an array, by setting it up as a heap, and removing the items from the
 * heap one by one. The "last" items in sort order is removed first, and
 * inserted into the space at the end of the heap that was created when the item
 * was removed.
 *
 * O(n * log(n))
 *
 * @author evenal
 */
public class HeapSort extends SortAlgorithm {

    int n;

    public HeapSort() {
        super("heapsort");
    }

    @Override
    public void sort() {
        n = getN();
        heapify();

        while (n > 1) {
            swap(0, n - 1);
            n--;
            siftDown(0);
        }
    }

    private void heapify() {
        for (int i = n / 2; i >= 0; i--) {
            siftDown(i);
        }
    }

    private void siftDown(int i) {
        int swapWith = 2 * i;
        if (swapWith < n) {
            int rightChild = swapWith + 1;

            if (rightChild < n && compare(rightChild, swapWith) > 0) {
                swapWith = rightChild;
            }
            if (compare(swapWith, i) > 0) {
                swap(i, swapWith);
                siftDown(swapWith);
            }
        }
    }

    @Override
    public double expectedCompares(int n) {
        return n * Math.log(n);
    }

    @Override
    public double expectedSwaps(int n) {
        return n * Math.log(n);
    }

    @Override
    public double expectedMoves(int n) {
        return n * Math.log(n);
    }
}
