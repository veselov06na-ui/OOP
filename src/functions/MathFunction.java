package functions;

/**
 * Represents a mathematical function that maps a single double input to a double output..
 */
public interface MathFunction {
    double apply(double x);

    /**
     * Returns composition g(f(x)) where this is f and afterFunction is g.
     */
    default CompositeFunction andThen(MathFunction afterFunction) {
        return new CompositeFunction(this, afterFunction);
    }
}
