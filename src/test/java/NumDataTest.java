package test.java;
import static org.junit.Assert.*;
import org.junit.Test;

import data.ComparedState;
import data.NumData;

public class NumDataTest {

    @Test
    public void valueTest() {
        NumData data = new NumData(3);
        assertEquals(data.value(), new Integer(3));
    }
    
    @Test
    public void compareTest() {
        NumData data = new NumData(3);
        ComparedState state1 = data.compare(new NumData(4));
        assertEquals(state1, ComparedState.LT);
        ComparedState state2 = data.compare(new NumData(3));
        assertEquals(state2, ComparedState.EQ);
        ComparedState state3 = data.compare(new NumData(2));
        assertEquals(state3, ComparedState.GT);
    }
    
    @Test
    public void getMiddleValue3Test() {
        NumData middle = NumData.getMiddleValue3(
                new NumData(3),
                new NumData(4),
                new NumData(5)
        );
        assertEquals(middle.value(), new NumData(4).value());
    }
}
