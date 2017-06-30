import sequence.NumDataSequence;
import sequence.NumSequenceGenerator;
import sort.simple.NumSimpleSorter;

public class Main {
    public static void main(String args[]) {
        NumDataSequence seq = NumSequenceGenerator.generateByShuffle(100, 0.9);
        NumSimpleSorter.insert(seq);
        seq.alphaShow();
    }
}
