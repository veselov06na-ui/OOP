package functions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConstantFunctionTest {

    @Test
    void returnsSameConstant() {
        ConstantFunction constant = new ConstantFunction(42.5);
        assertEquals(42.5, constant.apply(-10.0));
        assertEquals(42.5, constant.apply(0.0));
        assertEquals(42.5, constant.apply(999.0));
    }
}
