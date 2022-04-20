/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparesort;

/**
 * Sorts a list/array by picking the "first" remaining item in the input list,
 * and adding it to the end of the output list
 *
 * O(n*n)
 *
 * @author evenal
 */
public class SelectSort extends SortAlgorithm {

    public SelectSort() {
        super("selectSort");
    }

    @Override
    public void sort() {
        for (int i = 1; i < getN(); i++) {
            int minj = i - 1;
            for (int j = i; j < getN(); j++) {
                if (compare(j, minj) < 0) {
                    minj = j;
                }
            }
            swap(i - 1, minj);
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
