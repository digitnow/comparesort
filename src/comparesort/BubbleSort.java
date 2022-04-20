/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparesort;

/**
 * Sorts a list/array by swapping out of order neighbours.
 *
 * The worst of the O(n*n) algorithms included here
 *
 * @author evenal
 */
public class BubbleSort extends SortAlgorithm {

    public BubbleSort() {
        super("Bubblesort");
    }

    @Override
    public void sort() {
        boolean swapped = false;
        int n = getN();
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (compare(i - 1, i) > 0) {
                    swap(i, i - 1);
                    swapped = true;
                }
            }
        } while (swapped);
    }

    @Override
    public double expectedCompares(int n) {
        return n * n;
    }

    @Override
    public double expectedSwaps(int n) {
        return n * n;
    }

    @Override
    public double expectedMoves(int n) {
        return n * n;
    }
}
