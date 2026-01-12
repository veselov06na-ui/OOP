package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CompositeFunctionTest {

    @Test
    void composesIdentityAndSquare() {
        MathFunction identity = new IdentityFunction();
        MathFunction square = new SqrFunction();
        CompositeFunction composite = new CompositeFunction(square, identity); // identity(square(x)) == square(x)

        assertEquals(9.0, composite.apply(3.0));
    }

    @Test
    void composesSquareOfSquare() {
        MathFunction square = new SqrFunction();
        CompositeFunction composite = new CompositeFunction(square, square); // square(square(x)) == x^4

        assertEquals(16.0, composite.apply(2.0));
    }

    @Test
    void composesCompositeWithComposite() {
        MathFunction square = new SqrFunction();
        MathFunction identity = new IdentityFunction();

        CompositeFunction inner = new CompositeFunction(square, identity);            // identity(square(x)) == x^2
        CompositeFunction outer = new CompositeFunction(identity, square);            // square(identity(x)) == x^2
        CompositeFunction combined = new CompositeFunction(inner, outer);             // outer(inner(x)) == (x^2)^2 == x^4

        assertEquals(81.0, combined.apply(3.0));
    }
}
