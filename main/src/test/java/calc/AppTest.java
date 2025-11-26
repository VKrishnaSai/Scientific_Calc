package calc;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Unit tests for the Scientific Calculator App.
 */
public class AppTest {

    @Test
    public void testAddition() {
        App.Arithmetic arith = new App.Arithmetic();
        assertEquals(5.0, arith.addition(2.0, 3.0), 0.001);
    }

    @Test
    public void testSubtraction() {
        App.Arithmetic arith = new App.Arithmetic();
        assertEquals(1.0, arith.subtraction(5.0, 4.0), 0.001);
    }

    @Test
    public void testMultiplication() {
        App.Arithmetic arith = new App.Arithmetic();
        assertEquals(12.0, arith.multiplication(3.0, 4.0), 0.001);
    }

    @Test
    public void testDivision() {
        App.Arithmetic arith = new App.Arithmetic();
        assertEquals(2.5, arith.division(5.0, 2.0), 0.001);
    }

    @Test
    public void testDivisionByZero() {
        App.Arithmetic arith = new App.Arithmetic();
        assertEquals(0.0, arith.division(5.0, 0.0), 0.001);
    }

    @Test
    public void testExponent() {
        App.Power power = new App.Power();
        assertEquals(8.0, power.exponent(2.0, 3.0), 0.001);
    }

    @Test
    public void testSquareRoot() {
        App.Power power = new App.Power();
        assertEquals(3.0, power.root(9.0), 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSquareRootNegative() {
        App.Power power = new App.Power();
        power.root(-1.0);
    }

    @Test
    public void testCubeRoot() {
        App.Power power = new App.Power();
        assertEquals(2.0, power.cubeRoot(8.0), 0.001);
    }
}
