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
public class CounterSet {

    String algName;
    int dataSetSize;
    int iterations;
    long compares;
    double expectedCompares;
    long moves;
    double expectedMoves;
    long swaps;
    double expectedSwaps;

    public CounterSet(SortAlgorithm alg, int dataSize, int iterations) {
        this.algName = alg.getName();
        this.dataSetSize = dataSize;
        this.iterations = iterations;

        compares = 0; //alg.getCount(SortAlgorithm.COMPARE);
        expectedCompares = alg.expectedCompares(dataSize);
        moves = 0;
        expectedSwaps = alg.expectedSwaps(dataSize);
        swaps = 0;
        expectedMoves = alg.expectedMoves(dataSize);
    }

    public void incSwaps() {
        swaps++;
    }

    public void incMoves() {
        moves++;
    }

    public void incCompares() {
        compares++;
    }

}
