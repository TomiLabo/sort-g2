package sort.simple;

import sequence.NumDataSequence;

public class NumSimpleSorter {
    public static void select(NumDataSequence seq) {
        int n = seq.size();
        while (n > 1) {
            repeatForSelect(seq, n);
            n--;
        }
    }
    
    public static void insert(NumDataSequence seq) {
        int n = seq.size();
        while (n > 0) {
            n = repeatForInsert(seq, n);
        }
    }
    
    public static void gnome(NumDataSequence seq) {
        int k = 1;
        while (k < seq.size()) {
            if (seq.order(k-1, k)) { k++; continue; }
            seq.swap(k-1, k);
            k += k > 1 ? - 1 : 1;
        }
    }

    public static void trans(NumDataSequence seq) {
        boolean doing = true;
        while (doing) {
            doing = false;
            doing = repeatForTrans(seq, 0, seq.size());
            doing = repeatForTrans(seq, 1, seq.size());
        }
    }
    
    
    public static void shaker(NumDataSequence seq) {
        int i = 1;
        int j = seq.size() - 1;
        while (i < j) {
            for (int k = i; k < j; k++) { seq.swapIfNeeded(k - 1, k); }
            for (int k = j; k >= i; k--) { seq.swapIfNeeded(k - 1, k); }
            i++;
            j--;
        }
    }

    private static void repeatForSelect(NumDataSequence seq, int m) {
        int p = m - 1;
        for (int k = 0; k < m - 1; k++) {
            if (seq.order(p, k)) { p = k; }
        }
        seq.swap(p, m - 1);
    }

    private static boolean repeatForTrans(NumDataSequence seq, int s, int m) {
        for (int k = s; k < m - 1; k += 2) {
            if (!seq.order(k, k + 1)) {
                seq.swap(k, k + 1);
                return true;
            }
        }
        return false;
    }
    
    private static int repeatForInsert(NumDataSequence seq, int m) {
        int p = m;
        for (int k = 1; k < m; k++) {
            seq.swapIfNeeded(k - 1, k);
            p = k;
        }
        if (p == m) { return 0; }
        return p;
    }
}
