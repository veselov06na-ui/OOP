package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MathFunctionTest {

    @Test
    void andThenComposesTwoFunctions() {
        MathFunction identity = new IdentityFunction();
        MathFunction square = new SqrFunction();

        MathFunction composed = identity.andThen(square); // square(identity(x)) == x^2

        assertEquals(25.0, composed.apply(5.0));
    }

    @Test
    void andThenChainsThreeFunctions() {
        MathFunction square = new SqrFunction();          // x^2
        MathFunction plusOne = x -> x + 1;                // x + 1
        MathFunction timesTwo = x -> 2 * x;               // 2x

        MathFunction chain = square.andThen(plusOne).andThen(timesTwo); // 2*(x^2 + 1)

        assertEquals(20.0, chain.apply(3.0)); // 2*(9+1)=20
    }

    @Test
    void andThenWorksWithCompositeFunctions() {
        MathFunction square = new SqrFunction();                    // x^2
        MathFunction composite = square.andThen(square);            // x^4

        MathFunction chain = composite.andThen(new ZeroFunction()); // zero regardless of input

        assertEquals(0.0, chain.apply(2.0));
    }
}
