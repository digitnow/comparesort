package comparesort;

/**
 * ShellSort improves on insertionsiort, by moving items more than one position
 * at a time. It sorts every gap'th item, decreasing the size of the gap after
 * each iteration
 *
 * Runtime proven to be better than O(n**4/3). Exact performance not known.
 *
 * @author evenal
 */
public class ShellSort extends SortAlgorithm {

    private static int[] gaps = {701, 301, 132, 57, 23, 10, 4, 1};

    public ShellSort() {
        super("ShellSort");
    }

    @Override
    public void sort() {
        for (int gap : gaps) {
            for (int i = gap; i < getN(); i++) {
                int tmp = get(i);
                int j = i - 1;
                while (j > gap && get(j - gap) > tmp) {
                    counters.incCompares();
                    set(j, get(j - gap));
                    j = j - gap;
                    counters.incMoves();
                }
                set(j, tmp);
            }
        }
    }

    @Override
    public double expectedCompares(int n) {
        return Math.pow(n, 1.25);
    }

    @Override
    public double expectedSwaps(int n) {
        return Math.pow(n, 1.25);
    }

    @Override
    public double expectedMoves(int n) {
        return Math.pow(n, 1.25);
    }
}
