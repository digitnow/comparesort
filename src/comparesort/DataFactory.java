/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comparesort;

import java.util.Random;

/**
 *
 * @author evenal
 */
public class DataFactory {

    int n;
    int[] data;
    Random random;

    public DataFactory() {
        random = new Random();
    }

    public void setN(int n) {
        this.n = n;
    }

    public int[] getDataSet(int n) {
        data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = i;
        }
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i);
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }
        return data;
    }
}
