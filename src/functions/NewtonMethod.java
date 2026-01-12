package functions;

/**
 * Newton's method for root finding: returns an approximation of a root for a configured function.
 */
public class NewtonMethod implements MathFunction {
    private final MathFunction function;
    private final MathFunction derivative;
    private final double tolerance;
    private final int maxIterations;

    public NewtonMethod(MathFunction function, MathFunction derivative, double tolerance, int maxIterations) {
        this.function = function;
        this.derivative = derivative;
        this.tolerance = tolerance > 0 ? tolerance : 1e-10;
        this.maxIterations = Math.max(1, maxIterations);
    }

    @Override
    public double apply(double initialGuess) {
        double x = initialGuess;
        for (int i = 0; i < maxIterations; i++) {
            double f = function.apply(x);
            double df = derivative.apply(x);
            if (Math.abs(df) < 1e-12) {
                break; // avoid division by zero
            }
            double next = x - f / df;
            if (Math.abs(next - x) < tolerance) {
                return next;
            }
            x = next;
        }
        return x;
    }
}
