package functions;

/**
 * Represents a first-order ordinary differential equation y' = f(x, y).
 */
public interface DifferentialEquation {
    double dy(double x, double y);
}
