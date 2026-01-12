package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinkedListTabulatedFunctionTest {

    @Test
    void constructorBuildsListFromArrays() {
        double[] xs = {0.0, 1.0, 2.0};
        double[] ys = {0.0, 1.0, 4.0};
        LinkedListTabulatedFunction f = new LinkedListTabulatedFunction(xs, ys);

        assertEquals(3, f.getCount());
        assertEquals(0.0, f.getX(0));
        assertEquals(1.0, f.getX(1));
        assertEquals(2.0, f.getX(2));
        assertEquals(4.0, f.getY(2));
    }

    @Test
    void constructorDiscretizesFunction() {
        MathFunction source = x -> 2 * x;
        LinkedListTabulatedFunction f = new LinkedListTabulatedFunction(source, 0.0, 2.0, 3);

        assertEquals(0.0, f.getX(0));
        assertEquals(1.0, f.getX(1));
        assertEquals(2.0, f.getX(2));
        assertEquals(4.0, f.getY(2));
    }

    @Test
    void constructorSwapsBoundsWhenNeeded() {
        MathFunction source = x -> x;
        LinkedListTabulatedFunction f = new LinkedListTabulatedFunction(source, 2.0, -2.0, 5);

        assertEquals(-2.0, f.getX(0));
        assertEquals(2.0, f.getX(4));
    }

    @Test
    void singlePointHandling() {
        MathFunction source = x -> x + 1;
        LinkedListTabulatedFunction f = new LinkedListTabulatedFunction(source, 3.0, 3.0, 1);

        assertEquals(1, f.getCount());
        assertEquals(3.0, f.getX(0));
        assertEquals(4.0, f.getY(0));
        assertEquals(4.0, f.apply(100.0));
    }

    @Test
    void accessorsMutatorsAndSearch() {
        double[] xs = {1.0, 2.0, 3.0};
        double[] ys = {10.0, 20.0, 30.0};
        LinkedListTabulatedFunction f = new LinkedListTabulatedFunction(xs, ys);

        assertEquals(2.0, f.getX(1));
        assertEquals(20.0, f.getY(1));
        f.setY(1, 25.0);
        assertEquals(25.0, f.getY(1));
        assertEquals(1, f.indexOfX(2.0));
        assertEquals(2, f.indexOfY(30.0));
        assertEquals(-1, f.indexOfX(4.0));
        assertEquals(-1, f.indexOfY(999.0));
        assertEquals(1.0, f.leftBound());
        assertEquals(3.0, f.rightBound());
    }

    @Test
    void floorIndexHandlesEdges() {
        double[] xs = {-3.0, 4.0, 6.0};
        double[] ys = {0.0, 0.0, 0.0};
        LinkedListTabulatedFunction f = new LinkedListTabulatedFunction(xs, ys);

        assertEquals(0, f.floorIndexOfX(-10.0)); // all greater
        assertEquals(1, f.floorIndexOfX(4.5));   // between 4 and 6
        assertEquals(3, f.floorIndexOfX(10.0));  // all less
    }

    @Test
    void interpolationAndExtrapolationWork() {
        double[] xs = {0.0, 1.0, 2.0};
        double[] ys = {0.0, 1.0, 4.0};
        LinkedListTabulatedFunction f = new LinkedListTabulatedFunction(xs, ys);

        assertEquals(0.5, f.apply(0.5), 1e-12); // interpolate between 0 and 1
        assertEquals(4.0, f.apply(2.0), 1e-12); // exact point
        assertEquals(-1.0, f.apply(-1.0), 1e-12); // left extrapolation
        assertEquals(7.0, f.apply(3.0), 1e-12); // right extrapolation
    }

    @Test
    void constructorValidatesInput() {
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(null, new double[]{1}));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new double[]{1}, null));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(new double[]{1}, new double[]{1, 2}));
        assertThrows(IllegalArgumentException.class, () -> new LinkedListTabulatedFunction(x -> x, 0, 1, 0));
    }
}
