package data;

public class NumData {
    private Integer raw = 0;
    
    public NumData(Integer num) {
        this.raw = num;
    }
    
    public Integer value() {
        return this.raw;
    }
    
    public ComparedState compare(NumData d) {
        if (this.raw > d.value()) return ComparedState.EXCEED;
        else if (this.raw < d.value()) return ComparedState.LESS;
        else return ComparedState.SAME;
    }
}
