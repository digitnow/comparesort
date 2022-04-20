package comparesort;

/**
 * QuickSort is a recursive sort algoithm that uses a pivot (ideally the median
 * of the data) to divide the data into values smallere than the pivot, and
 * larger than the pilot. Then the large and small sets are sorted, and
 * concatenated to get the complete soerted dataset.
 *
 * O(n*log(n)) - QuickSort is generally considered the fastest sort algorithm.
 * However it has a worst case of O(n*n). If you have constraints, for eksample
 * that sorting must never take more than a certain amount of time, HeapSort
 * will be a better choice
 *
 * @author evenal
 */
public class QuickSort2 extends SortAlgorithm {

    private int minN = 10;

    public QuickSort2() {
        super("QuickSort2");
    }

    /**
     * This implementation of qsort is not the most efficient. Priority is given
     * to presenting the algorithm clearly
     */
    @Override
    public void sort() {
        qsort(0, getN());
    }

    /**
     * QuickSort works by selecting a pivot value, the values larger than the
     * pivot are moved to one part, and the larger values moved to the other
     * part. Then the parts are sorted recursively. When sorting an array, there
     * is room for both parts in the same array, the small values first and then
     * the small values, so when the parts have been sorted, sorting the whole
     * list is also finished.
     *
     * O(n*log(n) - QuickSort is the fastest of the "good" sort algorithms,
     * unfortunately there is a catch, if a naive implementation of quicksort is
     * given a sorted array, The performance will detoriorate to O(n*n). Clever
     * algorithms for picking the pivot can reduce the probability and impact of
     * hitting a worst case, but there will always be some input that will
     * trigger a worst case. This disqualifies quicksort from being a good
     * choice if you have realtime contstraints.
     *
     * Quicksort isn't a good choice for linked list either. The algorith works
     * best with indexed data, that is arrays.
     *
     * @param lo the index of the first value of the part that is being sorted
     * @param hi the index of the last+1 value of the part..
     */
    private void qsort(int lo, int hi) {
        if ((hi - lo) < minN) {
            insertSort(lo, hi);
        } else {
            int p = partition(lo, hi);
            qsort(lo, p);
            qsort(p + 1, hi);
        }
    }

    private void insertSort(int lo, int hi) {
        for (int i = lo; i < hi; i++) {
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

    /**
     * In quicksort the partitioning is doing all the work.
     *
     * @param lo
     * @param hi
     * @return
     */
    private int partition(int lo, int hi) {
        int i = lo;
        int j = hi - 1;
        int pivot = get(j);
        while (i < j) {
            while (get(i) < pivot) {
                i++;
            }
            while (get(j) > pivot) {
                j--;
            }
            if (i == j) {
                return j;
            } else {
                swap(i, j);
            }
        }
        return j;
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
