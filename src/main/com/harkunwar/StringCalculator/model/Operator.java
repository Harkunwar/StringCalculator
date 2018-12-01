package com.harkunwar.StringCalculator.model;


import java.math.BigDecimal;

@FunctionalInterface
public interface Operator {

    BigDecimal evaluate(BigDecimal value1, BigDecimal value2);

}
