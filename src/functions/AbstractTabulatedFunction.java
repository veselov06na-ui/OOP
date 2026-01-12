package functions;

/**
 * Base class for tabulated functions, providing common interpolation logic.
 */
public abstract class AbstractTabulatedFunction implements TabulatedFunction {
    protected int count;

    protected abstract int floorIndexOfX(double x);

    protected abstract double extrapolateLeft(double x);

    protected abstract double extrapolateRight(double x);

    protected abstract double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        if (rightX == leftX) {
            return leftY; // avoid division by zero; degenerate interval
        }
        double slope = (rightY - leftY) / (rightX - leftX);
        return leftY + slope * (x - leftX);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        }
        if (x > rightBound()) {
            return extrapolateRight(x);
        }
        int index = indexOfX(x);
        if (index != -1) {
            return getY(index);
        }
        int floorIndex = floorIndexOfX(x);
        return interpolate(x, floorIndex);
    }
}
