package sequence;

import java.util.Random;

import data.NumData;

public class NumSequenceGenerator {

    /**
     * {size}個だけ 0から始まる ソート済みのデータ列を生成する
     * @param size
     * @return NumDataSequence
     */
    public static NumDataSequence generateOfSorted(int size) {
        NumData[] seq = new NumData[size];
        for (int i = 0; i < size; i++) {
            seq[i] = new NumData(i);
        }
        return new NumDataSequence(seq);
    }
     
    /**
     * {size}個だけ randamな データ列を生成する
     * @param size
     * @return NumDataSequence
     */
    public static NumDataSequence generateByRandom(int size) {
        NumData[] seq = new NumData[size];
        Random randFunc = new Random();
        for (int i = 1; i < size; i++) {
            seq[i] = new NumData(randFunc.nextInt(i));
        }
        return new NumDataSequence(seq);
    }

    /**
     * 全データのうち，(probabolity / 1.00) * 100 % だけランダムなデータ列を生成する
     * @param size
     * @param probability {double} 0.00 ~ 1.00
     * @return NumDataSequence
     */
    public static NumDataSequence generateByShuffle(int size, double probability) {
        NumDataSequence dataSequence = NumSequenceGenerator.generateOfSorted(size);
        for (int i = 1; i < size; i++) {
            Random randFunc = new Random();
            if (Math.random() > probability) { continue; }
            int right = randFunc.nextInt(i);
            dataSequence.swap(i, right, false);
        }
        return dataSequence;
    }
    
    /**
     * count回 交換を行った データ列を生成する
     * @param size
     * @param count
     * @return
     */
    public static NumDataSequence generateByExchange(int size, int count) {
        NumDataSequence dataSequence = NumSequenceGenerator.generateOfSorted(size);
        Random randFunc = new Random();
        while (count > 0) {
            int p1 = randFunc.nextInt(size);
            int p2 = randFunc.nextInt(size);
            dataSequence.swap(p1, p2, false);
            count--;
        }
        return dataSequence;
    }
}
