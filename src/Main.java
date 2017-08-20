import data.NumData;
import out.NumDataOutput;
import sequence.NumDataSequence;
import sequence.NumSequenceGenerator;
import sort.algo.NumQuickSorter;
import sort.simple.NumSimpleSorter;

public class Main {
    public static void main(String args[]) {
        NumDataSequence seq = NumSequenceGenerator.generateByShuffle(100, 0.05);
        NumQuickSorter.sort(seq);
        NumDataOutput.puts(seq);
    }
}
