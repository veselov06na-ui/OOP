package functions;

/**
 * MathFunction implementation that returns its input unchanged.
 */
public class IdentityFunction implements MathFunction {
    @Override
    public double apply(double x) {
        return x;
    }
}
