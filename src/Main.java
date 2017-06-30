import out.NumDataOutput;
import sequence.NumDataSequence;
import sequence.NumSequenceGenerator;
import sort.simple.NumSimpleSorter;

public class Main {
    public static void main(String args[]) {
        // for (int j = 1; j < 2048; j++) {
        //     System.out.printf("%d,", j);
        //     NumDataSequence seq = NumSequenceGenerator.generateByExchange(1024, j);
        //     seq.clearRecord();
        //     NumSimpleSorter.gnome(seq);
        //     NumDataOutput.csv(seq);
        //     NumDataOutput.newLine();
        // }

        for (double i = 0.000; i < 1.0; i += 0.001) {
            System.out.printf("%f,", i);
            NumDataSequence seq = NumSequenceGenerator.generateByShuffle(1024, i);
            NumSimpleSorter.gnome(seq);
            NumDataOutput.csv(seq);
            NumDataOutput.newLine();
        }
    }
}
