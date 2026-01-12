package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnitFunctionTest {

    @Test
    void alwaysReturnsOne() {
        UnitFunction one = new UnitFunction();
        assertEquals(1.0, one.apply(-2.0));
        assertEquals(1.0, one.apply(0.0));
        assertEquals(1.0, one.apply(3.14));
    }
}
