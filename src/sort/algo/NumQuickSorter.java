package sort.algo;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import data.ComparedState;
import data.NumData;
import data.Pos;
import sequence.NumDataSequence;

public class NumQuickSorter {
    
    private NumDataSequence seq;
    
    private NumQuickSorter(final NumDataSequence seq) {
        this.seq = seq;
        
    }
    
    public static void sort(final NumDataSequence seq) {
        NumQuickSorter sorter = new NumQuickSorter(seq);
        sorter.quicksort3(new Pos(0, seq.size() - 1));
    }
    
    // private void quicksort3(Pos range) {
   //      // 打切条件
    //     // if (range.getLeft() >= range.getRight()) return;
   //      // 事前処理
   //      // キューの用意
    //     Queue<Pos> queue = new Queue<Pos>();
    //     queue.add(range);

    //     while (queue.size() != 0) {
    //         Pos separator = this.partition(queue.remove());
    //         Pos leftRange = new Pos(range.getLeft(), separator.getLeft() - 1);
    //         Pos rightRange = new Pos(separator.getRight() + 1, range.getRight());
    //         queue.add(rightRange);
    //         queue.add(leftRange);
    //     }
   //      // 再帰呼出
    //     // this.quicksort(leftRange);
    //     // this.quicksort(rightRange);
   //      // 事後処理
    //     // do nothing
    // }
    private void quicksort3(Pos range) {
        // 打切条件
        // if (range.getLeft() >= range.getRight()) return;
        // 事前処理
        // キューの用意
        Queue<Pos> queue = new ArrayDeque<Pos>();
        queue.add(range);

        while (queue.size() != 0) {
            Pos separator = this.partition(queue.poll());
            Pos leftRange = new Pos(range.getLeft(), separator.getLeft() - 1);
            Pos rightRange = new Pos(separator.getRight() + 1, range.getRight());
            if (rightRange.isInteraval() && rightRange.getRight() <= 50) queue.add(rightRange);
            if (leftRange.isInteraval() && leftRange.getLeft() <= 50) queue.add(leftRange);
        }
        // 再帰呼出
        // this.quicksort(leftRange);
        // this.quicksort(rightRange);
        // 事後処理
        // do nothing
    }
    private void quicksort2(Pos range) {
        // 打切条件
        // if (range.getLeft() >= range.getRight()) return;
        // 事前処理
        // スタックの用意
        Stack<Pos> stack = new Stack<Pos>();
        stack.push(range);

        while (stack.size() != 0) {
            Pos separator = this.partition(stack.pop());
            Pos leftRange = new Pos(range.getLeft(), separator.getLeft() - 1);
            Pos rightRange = new Pos(separator.getRight() + 1, range.getRight());
            if (rightRange.isInteraval()) stack.push(rightRange);
            if (leftRange.isInteraval()) stack.push(leftRange);
        }
        // 再帰呼出
        // this.quicksort(leftRange);
        // this.quicksort(rightRange);
        // 事後処理
        // do nothing
    }

    private void quicksort(Pos range) {
        // 打切条件
        if (range.getLeft() >= range.getRight()) return;
        // 事前処理
        // Pos tarmianl = this.partition(range);
        Pos separator = this.partition(range);
        Pos leftRange = new Pos(range.getLeft(), separator.getLeft() - 1);
        Pos rightRange = new Pos(separator.getRight() + 1, range.getRight());
        // 再帰呼出
        this.quicksort(leftRange);
        this.quicksort(rightRange);
        // 事後処理
        // do nothing
    }
    
    
    /**
     * クイックソートをする (再帰)
     * @param left {int}
     * @param right {int}
     * @return void
     */
    private void quicksort(int left, int right) {
        // 打切条件
        if (left >= right) return;
        // 事前処理
        Pos pos = this.partition(left, right);
        // 再帰呼出
        this.quicksort(left, pos.getLeft() - 1);
        this.quicksort(pos.getRight() + 1, right);
        // 事後処理
        // do nothing
    }
    
    private Pos partition(final Pos range) { 
        return this.partition(range.getLeft(), range.getRight());
    }

    /**
     * 振分処理
     * @param left {int}
     * @param right {int}
     * @return {int}
     */
    private Pos partition(int left, int right) { 
        NumData pivot = this.getPivot(left, right);
        int i = left, j = right;
        while (true) {
            // i < pivot < j
            while (this.seq.get(i).compare(pivot) == ComparedState.LT) i++;
            while (this.seq.get(j).compare(pivot) == ComparedState.GT) j--;
            if (i >= j) break;
            this.seq.swap(i, j);
        }
        return new Pos(i, j);
    }
    
    /**
     * quciksortに必要なpivotを計算する
     * @param left {int} ソート範囲の左端
     * @param right {int} ソート範囲の右端
     * @return {NumData}
     */
    private NumData getPivot(int left, int right) {
        NumData mv = this.seq.get((left + right) / 2);
        NumData lv = this.seq.get(left);
        NumData rv = this.seq.get(right);
        return NumData.getMiddleValue3(lv, mv, rv);
    }
}