/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparesort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author evenal
 */
public class Experiment {

    public static int NUM_ITERATIONS = 1000;

    public static void main(String[] args) {
        Experiment exp = new Experiment(100, 10001,
                                        StepType.EXPONENTIAL, 10);
        exp.addAlgorithm(new InsertSort());
        exp.addAlgorithm(new QuickSort());
        exp.addAlgorithm(new QuickSort2());
        exp.run();
    }

    List<SortAlgorithm> algorithms;
    int datasetMin, datasetMax;
    int growthStep;
    StepType type;
    DataFactory dataFactory;
    int numIterations;

    public Experiment(int nmin, int nmax, StepType type, int step) {
        this(new ArrayList<SortAlgorithm>(), nmin, nmax, type, step);
    }

    public Experiment(List<SortAlgorithm> algorithms,
                      int nmin, int nmax, StepType type, int step) {
        this.algorithms = algorithms;
        this.datasetMin = nmin;
        this.datasetMax = nmax;
        this.type = type;
        this.growthStep = step;
        this.numIterations = 100;
        this.dataFactory = new DataFactory();
    }

    public void addAlgorithm(SortAlgorithm alg) {
        algorithms.add(alg);
    }

    public void run() {
        List<CounterSet> summaries = null;
        for (int n = datasetMin;
                n < datasetMax;
                n = (type == StepType.LINEAR)
                        ? n + growthStep
                        : n * growthStep) {
            runSizeStep(n);
        }
    }

    private void runSizeStep(int dataSetSize) {
        List<CounterSet> results = new ArrayList<>();
        for (SortAlgorithm alg : algorithms) {
            CounterSet result = new CounterSet(alg, dataSetSize, numIterations);
            alg.setCounters(result);
            results.add(result);
        }

        for (int i = 0; i < numIterations; i++) {
            int[] dataSet = dataFactory.getDataSet(dataSetSize);
            for (SortAlgorithm alg : algorithms) {
                int[] data = Arrays.copyOf(dataSet, dataSetSize);
                alg.setData(data);
                alg.sort();
                assert alg.didSort();
            }
        }
        printResult(results, dataSetSize);
    }

    public void printResult(List<CounterSet> summaries, int n) {
        System.out.format("\n====================\nn=%d\n", n);
        System.out.format("Algorithm: ");
        for (CounterSet c : summaries) {
            System.out.format("%19s ", c.algName);
        }
        System.out.format("\n Compares: ");
        for (CounterSet c : summaries) {
            System.out.format("%10d/(%8.4f) ", c.compares, c.compares / (c.expectedCompares));
        }
        System.out.format("\n    Moves: ");
        for (CounterSet c : summaries) {
            System.out.format("%10d/(%8.4f) ", c.moves, c.moves / (c.expectedMoves));
        }
        System.out.format("\n    Swaps: ");
        for (CounterSet c : summaries) {
            System.out.format("%10d/(%8.4f) ", c.swaps, c.swaps / (c.expectedSwaps));
        }
        System.out.format("\n");
    }

    public enum StepType {
        LINEAR, EXPONENTIAL;
    }
}
