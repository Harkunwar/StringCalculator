package com.harkunwar.StringCalculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class OperatorList implements Iterable<Map.Entry<String, Operator>>{

    private Map<String, Operator> operators;
    private int precision = 10;

    OperatorList() {
        operators = new LinkedHashMap<>();
        this.initializeDefaultOperators();
    }

    private void initializeDefaultOperators() {

        Operator POWER = (x, y) -> new BigDecimal(Math.pow(x.doubleValue(),y.doubleValue()));
        this.add("^", POWER);

        Operator MULTIPLY = BigDecimal::multiply;
        this.add("*", MULTIPLY);

        Operator DIVIDE = (x, y) -> x.divide(y,precision,RoundingMode.HALF_UP);
        this.add("/", DIVIDE);

        Operator REMAINDER = BigDecimal::remainder;
        this.add("%", REMAINDER);

        Operator ADD = BigDecimal::add;
        this.add("+", ADD);

        Operator SUBTRACT = BigDecimal::subtract;
        this.add("-", SUBTRACT);

    }

    private void add(String operator, Operator function) {
        operators.put(operator,function);
    }

    public Set<String> getOperators() {
        return operators.keySet();
    }

    @Override
    public Iterator<Map.Entry<String, Operator>> iterator() {
        return operators.entrySet().iterator();
    }
}
