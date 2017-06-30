package out;

import data.NumData;
import sequence.NumDataSequence;

public class NumDataOutput {
    public static void csv(final NumDataSequence seq) {
        final String compCount = toString(seq.getCompareCount());
        final String swapCount = toString(seq.getSwapCount());
        final String str = compCount + "," + swapCount + ",";
        System.out.print(str);
    }
    
    public static void puts(final NumDataSequence seq) {
        System.out.print("[ ");
        for (NumData d: seq.getAll()) {
            final String s = toString(d.value());
            System.out.printf("%s ", s);
        }
        System.out.println("]");
    }
   
    public static void newLine() {
        System.out.println();
    }
    
    private static String toString(int v) {
        return Integer.toString(v);
    }
}
