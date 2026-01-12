package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class IdentityFunctionTest {
    private final MathFunction identity = new IdentityFunction();

    @Test
    void returnsSameValueForPositive() {
        assertEquals(3.14, identity.apply(3.14));
    }

    @Test
    void returnsSameValueForNegative() {
        assertEquals(-2.0, identity.apply(-2.0));
    }

    @Test
    void returnsSameValueForZero() {
        assertEquals(0.0, identity.apply(0.0));
    }
}
