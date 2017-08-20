package data;

public class NumData {
    private Integer raw = 0;
    
    public NumData(Integer num) {
        this.raw = num;
    }
    
    public Integer value() {
        return this.raw;
    }
    
    /**
     * <pre>a < b < c </pre> のとき，trueを返す
     * それ以外のとき，falseを返す
     * @param a {NumData}
     * @param b {NumData}
     * @param c {NumData}
     * @return {NumData}
     */
    private static boolean within(NumData a, NumData b, NumData c) {
        // !(a <= b && b <= c)
        // (a > b || b > c)
        if (a.compare(b) == ComparedState.GT 
            || b.compare(c) == ComparedState.GT) return false;
        return true;
    }

    /**
     * 中央値を返却する
     * @param {NumData} a
     * @param {NumData} b
     * @param {NumData} c
     * @return {NumData}
     */
    public static NumData getMiddleValue3(final NumData a, final NumData b, final NumData c) {
        if (within(b, a, c)) return a;
        else if (within(c, a, b)) return a;
        else if (within(a, b, c)) return b;
        else if (within(c, b, a)) return b;
        return c;
    }
    
    public ComparedState compare(NumData d) {
        if (this.raw > d.value()) return ComparedState.GT;
        else if (this.raw < d.value()) return ComparedState.LT;
        else return ComparedState.EQ;
    }
}
