package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RungeKuttaMethodTest {

    @Test
    void solvesExponentialGrowth() {
        DifferentialEquation eq = (x, y) -> y; // y' = y, solution y = y0 * e^(x - x0)
        RungeKuttaMethod solver = new RungeKuttaMethod(eq, 0.01, 0.0, 1.0);

        double yAt1 = solver.apply(1.0);

        assertEquals(Math.exp(1.0), yAt1, 1e-4);
    }

    @Test
    void handlesNegativeDirection() {
        DifferentialEquation eq = (x, y) -> -2.0 * y; // y' = -2y, solution y = y0 * e^{-2(x - x0)}
        RungeKuttaMethod solver = new RungeKuttaMethod(eq, 0.05, 1.0, 2.0);

        double yAt0 = solver.apply(0.0);

        double expected = 2.0 * Math.exp(-2.0 * (0.0 - 1.0));
        assertEquals(expected, yAt0, 1e-3);
    }
}
