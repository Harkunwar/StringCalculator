package com.harkunwar.StringCalculator;

import com.harkunwar.StringCalculator.model.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class OperatorListTest {

    private StringCalculator calculator;

    @BeforeEach
    void beforeEach() {
        calculator = new StringCalculator();
        calculator.setPrecision(5);
    }

    @Test
    void testAdd() {
        assertEquals(calculator.solve("45.1+90"), new BigDecimal("135.1"));
        assertEquals(calculator.solve("100+-99"), new BigDecimal("1"));
        assertEquals(calculator.solve("-100+2"), new BigDecimal("-98"));
    }

    @Test
    void testSubtract() {
        assertEquals(calculator.solve("100-99"), new BigDecimal("1"));
        assertEquals(calculator.solve("-98-2"), new BigDecimal("-100"));
    }

    @Test
    void testMultiply() {
        assertEquals(calculator.solve("90*5"), new BigDecimal("450"));
        assertEquals(calculator.solve("2.2*2"), new BigDecimal("4.4"));
    }

    @Test
    void testDivide() {
        assertEquals(calculator.solve("100/25"), new BigDecimal("4"));
        assertEquals(calculator.solve("2/5"), new BigDecimal("0.4"));
        assertNull(calculator.solve("1/0"));
    }

    @Test
    void testPower() {
        assertEquals(calculator.solve("10^2"), new BigDecimal("100"));
        assertEquals(calculator.solve("2^2"), new BigDecimal("4"));
        assertEquals(calculator.solve("PI^0"), new BigDecimal("1"));
    }

    @Test
    void testMod() {
        assertEquals(calculator.solve("10%2"), new BigDecimal("0"));
        assertEquals(calculator.solve("2%10"), new BigDecimal("2"));
    }

    @Test
    void testMultiple() {
        assertEquals(calculator.solve("2+2+2-2-2-2"),new BigDecimal("0"));
        assertEquals(calculator.solve("10/2*10"),new BigDecimal("0.5"));
        assertEquals(calculator.solve("(10/2)*10"),new BigDecimal("50"));
    }



}