package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NewtonMethodTest {

    @Test
    void findsSqrtTwoRoot() {
        MathFunction f = x -> x * x - 2.0;
        MathFunction df = x -> 2.0 * x;
        NewtonMethod method = new NewtonMethod(f, df, 1e-12, 50);

        double root = method.apply(1.0);

        assertEquals(Math.sqrt(2.0), root, 1e-8);
    }

    @Test
    void convergesOnLinearFunction() {
        MathFunction f = x -> 3 * x - 9; // root at x=3
        MathFunction df = x -> 3;
        NewtonMethod method = new NewtonMethod(f, df, 1e-10, 10);

        double root = method.apply(0.0);

        assertEquals(3.0, root, 1e-10);
    }
}
