package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompositeTabulatedFunctionTest {

    @Test
    void arrayThenLinkedComposition() {
        // f(x) = x^2 tabулировано в массиве на [0,2]
        ArrayTabulatedFunction f = new ArrayTabulatedFunction(new SqrFunction(), 0.0, 2.0, 3);
        // g(x) = 2x + 1 табулировано в списке на [0,2]
        LinkedListTabulatedFunction g = new LinkedListTabulatedFunction(x -> 2 * x + 1, 0.0, 2.0, 3);

        MathFunction h = f.andThen(g); // g(f(x))

        double expected = g.apply(f.apply(1.5));
        assertEquals(expected, h.apply(1.5), 1e-12);
    }

    @Test
    void linkedThenArrayComposition() {
        // g(x) = 2x табулировано в списке на [0,2]
        LinkedListTabulatedFunction g = new LinkedListTabulatedFunction(x -> 2 * x, 0.0, 2.0, 3);
        // f(x) = x^2 табулировано в массиве на [0,2]
        ArrayTabulatedFunction f = new ArrayTabulatedFunction(new SqrFunction(), 0.0, 2.0, 3);

        MathFunction h = g.andThen(f); // f(g(x))

        assertEquals(1.0, h.apply(0.5), 1e-12); // g(0.5)=1 -> f(1)=1
    }

    @Test
    void tabulatedAndThenRegularFunction() {
        // t(x) = x + 1 табулировано в массиве на [0,2]
        ArrayTabulatedFunction t = new ArrayTabulatedFunction(x -> x + 1, 0.0, 2.0, 3);
        MathFunction sqr = new SqrFunction();

        MathFunction h = t.andThen(sqr); // (x+1)^2

        assertEquals(6.25, h.apply(1.5), 1e-12);
    }

    @Test
    void regularThenTabulatedFunction() {
        MathFunction plusOne = x -> x + 1;
        // identity табулировано в списке на [0,4]
        LinkedListTabulatedFunction identityTab = new LinkedListTabulatedFunction(x -> x, 0.0, 4.0, 5);

        MathFunction h = plusOne.andThen(identityTab); // tabulated identity of (x+1)

        assertEquals(2.5, h.apply(1.5), 1e-12);
    }
}
