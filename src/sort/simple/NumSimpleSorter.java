package sort.simple;

import sequence.NumDataSequence;

public class NumSimpleSorter {
    public static void select(NumDataSequence seq) {
        int p = -1;
        for (int i = 0; i < seq.size(); i++) {
            p = i;
            for (int j = i + 1; j < seq.size(); j++) {
                if (!seq.order(p, j)) { p = j; }
            }
            seq.swap(i, p);
        }
    }
    
    public static void insert(NumDataSequence seq) {
        for (int i = 0; i < seq.size() - 1; i++) {
            for (int j = i; j >= 0; j--) {
                if (seq.order(j, j + 1)) break;
                seq.swap(j, j + 1);
            }
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
            for (int k = 0; k < seq.size() - 1; k += 2) {
                if (!seq.order(k, k + 1)) {
                    seq.swap(k, k + 1);
                    doing = true;
                }
            }
            for (int k = 1; k < seq.size() - 1; k += 2) {
                if (!seq.order(k, k + 1)) {
                    seq.swap(k, k + 1);
                    doing = true;
                }
            }
        }
    }
    
    public static void shaker(NumDataSequence seq) {
        int i = 1;
        int j = seq.size() - 1;
        while (i < j) {
            for (int k = i; k < j; k++) {
                if (!seq.order(k - 1, k)) { seq.swap(k - 1, k); }
            }
            for (int k = j; k < i; k--) {
                if (!seq.order(k - 1, k)) { seq.swap(k - 1, k); }
            }
            i++;
            j--;
        }
    }
}
