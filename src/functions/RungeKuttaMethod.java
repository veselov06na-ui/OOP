package functions;

/**
 * Classic 4th-order Runge-Kutta solver for a fixed first-order ODE y' = f(x, y).
 * The solver returns y at a requested x, starting from configured initial conditions.
 */
public class RungeKuttaMethod implements MathFunction {
    private final DifferentialEquation equation;
    private final double step;
    private final double x0;
    private final double y0;

    public RungeKuttaMethod(DifferentialEquation equation, double step, double x0, double y0) {
        this.equation = equation;
        this.step = step > 0 ? step : 0.01;
        this.x0 = x0;
        this.y0 = y0;
    }

    @Override
    public double apply(double targetX) {
        double hSigned = step * Math.signum(targetX - x0 == 0 ? 1 : targetX - x0);
        double x = x0;
        double y = y0;
        double remaining = targetX - x;

        while (Math.abs(remaining) > 1e-12) {
            double currentStep = Math.abs(remaining) < Math.abs(hSigned) ? remaining : hSigned;
            double k1 = equation.dy(x, y);
            double k2 = equation.dy(x + currentStep / 2.0, y + currentStep * k1 / 2.0);
            double k3 = equation.dy(x + currentStep / 2.0, y + currentStep * k2 / 2.0);
            double k4 = equation.dy(x + currentStep, y + currentStep * k3);
            y += (currentStep / 6.0) * (k1 + 2 * k2 + 2 * k3 + k4);
            x += currentStep;
            remaining = targetX - x;
        }
        return y;
    }
}
