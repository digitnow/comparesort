/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparesort;

/**
 * Sorts a list/array by traversing the input list, adding each item to the
 * output list. The code below makes use of the empty position that is created
 * when an item is removed
 *
 * O(n*n) - this is the fastest of the quadratic algorithms. If the data is
 * "almost sorted" the runtime will be closer to O(n)
 *
 * @author evenal
 */
public class InsertSort extends SortAlgorithm {

    public InsertSort() {
        super("insertsort");
    }

    @Override
    public void sort() {
        for (int i = 1; i < getN(); i++) {
            int x = get(i);
            int j = i - 1;

            while (j >= 0 && get(j) > x) {
                counters.incCompares();
                move(j, j + 1);
                j = j - 1;
            }
            set(j + 1, x);
        }
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
