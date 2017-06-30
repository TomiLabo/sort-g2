package sequence;

import java.util.Arrays;
import java.util.Collections;
import data.ComparedState;
import data.NumData;

/**
 * データ列を管理するクラス
 * 自身の状態の管理が責務
 * 他のデータシーケンスとの併合や交換は行わない
 * @author maxmellon
 */
public class NumDataSequence {
    private int compareCount = 0;
    private int swapCount = 0;
    private NumData[] raw;

    public NumDataSequence(final NumData[] dataList) {
        this.raw = dataList;
    }
    
    /**
     * <b>現在の交換回数と比較回数をクリアします</b>
     * @return void
     */
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
     * this.raw = dataList;<br />
     * this.raw が [ 0, 2, 1 ] のとき，swap(1, 2) すると this.raw が [ 0, 1, 2 ] になる
     * @paraml {int} p1 スワップ前の左側の添字
     * @paramr {int} p2 スワップ前の右側の添字
     * @return void
     */
    public void swap(int p1, int p2) {
        swapCount++;
        Collections.swap(Arrays.asList(this.raw), p1, p2);
    }
    
    /**
     * <b>swapの必要があればスワップする<b/><br />
     * @param p1 {int}
     * @param p2 {int}
     * @return boolean スワップしたかどうか
     */
    public boolean swapIfNeeded(int p1, int p2) {
        if (!this.order(p1, p2)) {
            this.swap(p1, p2);
            return true;
        }
        return false;
    }
    
    /**
     * <b>配列の要素のp1とp2が正しい順番か判定します</b><br />
     * raw[p1] ＜ raw[p2] のとき True を返します
     * @param p1 {int}
     * @param p2 {int}
     * @return boolean
     */
    public boolean order(int p1, int p2) {
        compareCount++;
        return this.orderByLower(p1, p2);
    }

    /**
     * <b>配列の要素のp1とp2を比較します．</b><br />
     * ASCを指定したときは昇順，DESCを指定したときは降順であるかを返します．
     * @param type {OrderType} ASC | DESC
     * @param p1 {int}
     * @param p2 {int}
     * @return boolean
     */
    public boolean orderBy(int p1, int p2, OrderType type) {
        compareCount++;
        switch (type) {
        case ASC:
            return this.orderByLower(p1, p2);
        case DESC:
            return this.orderByOver(p1, p2);
        default:
            return false;
        }
    }

    /**
     * <b>保持しているシーケンスデータをコピーして返却します</b><br />
     * debug出力する際はこの関数を用いて，データを取得してください
     * @return NumData[]
     */
    public NumData[] getAll() {
        // retutn copy object, because `this.raw` should not be written by outside.
        NumData array[] = new NumData[this.raw.length];
        for (int i = 0; i < this.raw.length; i++) {
            array[i] = new NumData(this.raw[i].value());
        }
        return array;
    }
    
    /**
     * <b>現在保持しているデータ数を返却します</b><br />
     * @return int
     */
    public int size() { return this.raw.length; }

    /**
     * <b>現在保持しているデータ数を返却します</b><br />
     * Aliased of size()
     * @return int
     */
    public int length() { return this.raw.length; }
    
    /**
     * 現在の比較回数を返す
     * @return int
     */
    public int getCompareCount() { return this.compareCount; }
    
    /**
     * 現在の交換回数を返す
     * @return
     */
    public int getSwapCount() { return this.swapCount; }
    
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
     * @param range {int} 0 から range番目まで の range
     * @return boolean
     */
    public boolean isSorted(int range) {
        return this.isSorted(range, OrderType.ASC);
    }

    /**
     * <b>自身のデータが <i>range番目まで</i>ソート済みかどうかを判定する</b><br />
     * また，<i>ASC</i>が与えられたときは昇順<i>DESC</i>が
     * 与えられたときは降順でソートされているかを調べる．
     * @param range {int}
     * @param type {OrderType} ASC | DESC
     * @return boolean
     */
    public boolean isSorted(int range, OrderType type) {
        for (int i = 1; i < range; i++) {
            switch (type) {
            case ASC:
                if (this.orderBy(i - 1, i, OrderType.ASC)) { return false; }
            case DESC:
                if (this.orderBy(i - 1, i, OrderType.DESC)) { return false; }
            }
        }
        return true;
    }

    
    /**
     * <b>p1番目がp2番目の要素以上であるとき，trueを返す</b>
     * @param p1 {int}
     * @param p2 {int}
     * @return boolean
     */
    private boolean orderByOver(int p1, int p2) {
        ComparedState state = this.raw[p1].compare(this.raw[p2]);
        return state == ComparedState.EXCEED || state == ComparedState.SAME;
    } 

    /**
     * <b>p1番目がp2番目の要素以下であるとき，trueを返す</b>
     * @param p1 {int}
     * @param p2 {int}
     * @return boolean
     */
    private boolean orderByLower(int p1, int p2) {
        ComparedState state = this.raw[p1].compare(this.raw[p2]);
        return state == ComparedState.LESS || state == ComparedState.SAME;
    } 
}
