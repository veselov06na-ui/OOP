package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SqrFunctionTest {
    private final MathFunction sqr = new SqrFunction();

    @Test
    void squaresPositiveValue() {
        assertEquals(4.0, sqr.apply(2.0));
    }

    @Test
    void squaresNegativeValue() {
        assertEquals(9.0, sqr.apply(-3.0));
    }

    @Test
    void squaresFractionalValue() {
        assertEquals(0.25, sqr.apply(0.5), 1e-12);
    }

    @Test
    void squaresZero() {
        assertEquals(0.0, sqr.apply(0.0));
    }
}
