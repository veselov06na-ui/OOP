package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ZeroFunctionTest {

    @Test
    void alwaysReturnsZero() {
        ZeroFunction zero = new ZeroFunction();
        assertEquals(0.0, zero.apply(-5.0));
        assertEquals(0.0, zero.apply(0.0));
        assertEquals(0.0, zero.apply(7.3));
    }
}
