package functions;

/**
 * MathFunction that returns the square of the input using {@link Math}.
 */
public class SqrFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return Math.pow(x, 2);
    }
}
