import sequence.NumDataSequence;
import sequence.NumSequenceGenerator;

public class Main {
    public void main(String args[]) {
        NumDataSequence seq = NumSequenceGenerator.generateOfSorted(100);
        seq.alphaShow();
    }
}
