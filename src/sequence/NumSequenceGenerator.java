package sequence;

import java.util.Random;

import data.NumData;

public class NumSequenceGenerator {

    public static NumDataSequence generateOfSorted(int size) {
        NumData[] seq = new NumData[size];
        for (int i = 0; i < size; i++) {
            seq[i] = new NumData(i);
        }
        return new NumDataSequence(seq);
    }
     
    public static NumDataSequence generateByRandom(int size) {
        NumData[] seq = new NumData[size];
        Random randFunc = new Random();
        for (int i = 0; i < size; i++) {
            seq[i] = new NumData(randFunc.nextInt(size));
        }
        return new NumDataSequence(seq);
    }
    
    public static NumDataSequence generateByShuffle(int size, double probability) {
        NumDataSequence dataSequence = NumSequenceGenerator.generateOfSorted(size);
        for (int i = 0; i < size; i++) {
            Random randFunc = new Random();
            if (Math.random() > probability) { continue; }
            int right = randFunc.nextInt(i);
            dataSequence.swap(i, right);
        }
        return dataSequence;
    }
    
    public static NumDataSequence generateByExchange(int size, int count) {
        NumDataSequence dataSequence = NumSequenceGenerator.generateOfSorted(size);
        Random randFunc = new Random();
        while (count > 0) {
            int p1 = randFunc.nextInt(size);
            int p2 = randFunc.nextInt(size);
            dataSequence.swap(p1, p2);
            count--;
        }
        return dataSequence;
    }
}
