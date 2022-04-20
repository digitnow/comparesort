/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparesort;

/**
 *
 * @author evenal
 */
public abstract class SortAlgorithm {

    protected String name;
    protected int[] data;
    protected CounterSet counters;

//    private HashMap<String, Long> counters;
    public SortAlgorithm(String name) {
        this.name = name;
    }

    public final String getName() {
        return name;
    }

    public final int get(int i) {
        return data[i];
    }

    public final int getN() {
        return data.length;
    }

    public void printData() {
        System.out.print("Data=[");
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                System.out.format(" %4d", data[i]);
            } else {
                System.out.format(", %4d", data[i]);
            }
        }
        System.out.println();
    }

    public void setData(int[] data) {
        this.data = data;
    }

    public void setCounters(CounterSet counters) {
        this.counters = counters;
    }

    protected final void swap(int i1, int i2) {
        int tmp = data[i1];
        data[i1] = data[i2];
        data[i2] = tmp;
        counters.incSwaps();
    }

    protected final void move(int from, int to) {
        data[to] = data[from];
        counters.incMoves();
    }

    protected final void set(int i, int val) {
        data[i] = val;
        counters.incMoves();
    }

    /**
     * Compare data[i1] and data[i2].
     *
     * @param i1 index of first number to compare
     * @param i2 index of second number to compare
     *
     * @return a positive number if data[i1] is largest, a negative number if
     * data[i2] i largest, or zero if they are equal. (The actual return value
     * is data[i1] - data[i2])
     */
    protected final int compare(int i1, int i2) {
        counters.incCompares();
        return data[i1] - data[i2];
    }

    public abstract void sort();

    public boolean didSort() {
        for (int i = 1; i < data.length; i++) {
            if (data[i - 1] > data[i]) {
                return false;
            }
        }
        return true;
    }

    public abstract double expectedCompares(int n);

    public abstract double expectedSwaps(int n);

    public abstract double expectedMoves(int n);
}
