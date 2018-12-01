package com.harkunwar.StringCalculator.model;

import java.math.BigDecimal;

@FunctionalInterface
public interface Function {

    BigDecimal evaluate(BigDecimal input);

}
