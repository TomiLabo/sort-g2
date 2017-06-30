package sequence;

import java.util.Arrays;
import java.util.Collections;
import data.ComparedState;
import data.NumData;

public class NumDataSequence {
    public int compareCount = 0;
    public int swapCount = 0;
    private NumData[] raw;

    public NumDataSequence(final NumData[] dataList) {
        this.raw = dataList;
    }
    
    public void clearRecord() {
        compareCount = 0;
        swapCount = 0;
    }
    /**
     * <b>現在の配列の状態を出力する</b><br />
     * 例 : [ 1, 3, 4, 0, 2 ]
     * @return void
     */
    public void alphaShow() {
        System.out.print("[ ");
        for (NumData data: raw) {
            System.out.printf("%d ", data.value());
        }
        System.out.print("]\n");
    }
    
    /**
     * <b>l番目のデータとr番目のデータを入れ替える</b><br />
     * this.raw = dataList;
     * this.raw が [ 0, 2, 1 ] のとき，swap(1, 2) すると this.raw が [ 0, 1, 2 ] になる
     * @paraml {int} p1 スワップ前の左側の添字
     * @paramr {int} p2 スワップ前の右側の添字
     * @return void
     */
    public void swap(int p1, int p2) {
        swapCount++;
        Collections.swap(Arrays.asList(this.raw), p1, p2);
    }

    public boolean sortIfNeeded(int p1, int p2) {
        if (this.order(p1, p2)) {
            this.swap(p1, p2);
            return true;
        }
        return false;
    }
    
    public boolean order(int p1, int p2) {
        compareCount++;
        return this.orderByOver(p1, p2);
    }

    public boolean orderBy(OrderType type, int p1, int p2) {
        compareCount++;
        switch (type) {
        case ASC:
            return this.orderByOver(p1, p2);
        case DESC:
            return this.orderByLower(p1, p2);
        default:
            return false;
        }
    }

    public NumData[] getAll() {
        // retutn copy object, because `this.raw` should not be written by outside.
        NumData array[] = new NumData[this.raw.length];
        for (int i = 0; i < this.raw.length; i++) {
            array[i] = new NumData(this.raw[i].value());
        }
        return array;
    }
    
    public int size() {
        return this.raw.length;
    }
    
    /**
     * <b>自身のデータがソート済みかどうかを判定する</b><br />
     * 例1 : this.raw が [ 0, 1, 2, 3 ] のとき // => true <br />
     * 例2 : this.raw が [ 0, 2, 1, 2 ] のとき // => false 
     * @return boolean
     */
    public boolean isSorted() {
        return this.isSorted(this.raw.length, OrderType.ASC);
    }

    /**
     * <b>自身のデータが <i>range番目まで</i>ソート済みかどうかを判定する</b><br />
     * @param range 0 から range番目まで の range
     * @return boolean
     */
    public boolean isSorted(int range) {
        return this.isSorted(range, OrderType.ASC);
    }

    public boolean isSorted(int range, OrderType type) {
        for (int i = 1; i < range; i++) {
            switch (type) {
            case ASC:
                if (this.orderByLess(i - 1, i)) { return false; }
            case DESC:
                if (this.orderByExceed(i - 1, i)) { return false; }
            }
        }
        return true;
    }

    private boolean orderByOver(int p1, int p2) {
        ComparedState state = this.raw[p1].compare(this.raw[p2]);
        return state == ComparedState.EXCEED || state == ComparedState.SAME;
    } 

    private boolean orderByLower(int p1, int p2) {
        ComparedState state = this.raw[p1].compare(this.raw[p2]);
        return state == ComparedState.LESS || state == ComparedState.SAME;
    } 

    private boolean orderByLess(int p1, int p2) {
        return this.raw[p1].compare(this.raw[p2]) == ComparedState.LESS;
    } 

    private boolean orderByExceed(int p1, int p2) {
        return this.raw[p1].compare(this.raw[p2]) == ComparedState.EXCEED;
    }
}
