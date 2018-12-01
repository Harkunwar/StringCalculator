package com.harkunwar.StringCalculator.model;

import com.harkunwar.StringCalculator.exceptions.InvalidExpressionException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StringCalculator {

    int precision;
    ExpressionHistory history;
    OperatorList operatorList;
    Constants constants;
    FunctionList functionList;
    BigDecimal result;
    String currentExpression;

    //MODIFIES: this
    //EFFECTS: initializes the calculator
    public StringCalculator() {
        this(new ExpressionHistory());
    }

    public StringCalculator(ExpressionHistory history) {
        this.precision = 5;
        this.result = new BigDecimal("0");
        this.history = history;
        this.operatorList = new OperatorList();
        this.constants = new Constants();
        this.functionList = new FunctionList();
    }

    //REQUIRES: precision greater than 1
    //MODIFIES: this
    //EFFECTS: sets the precision of the evaluations to be of that value
    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public void setExpression(String expression) {

        this.currentExpression = expression.replaceAll("\\s+","");;
    }

    public BigDecimal evaluate() throws InvalidExpressionException {
        Solver solver = new Solver(currentExpression, this);
        BigDecimal result = solver.solve();
        history.put(currentExpression,result);
        return result;
    }

    public BigDecimal solve(String expression) {
        try {
            setExpression(expression);
            return evaluate();
        }
        catch (Exception exception) {
            return null;
        }
    }


    public boolean checkValidExpression(String expression) throws InvalidExpressionException {
        for(int i=0; i<expression.length(); i++) {

        }
        return true;
    }

    private void addToHistory(String expression, BigDecimal result) {
        history.put(expression,result);
    }

    public ExpressionHistory getHistory() {
        return history;
    }

    public int getPrecision() {
        return precision;
    }


}
