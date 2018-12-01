package com.harkunwar.StringCalculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

class Solver {

    private String expression;
    private BigDecimal result;
    private StringCalculator stringCalculator;


    Solver(String expression, StringCalculator stringCalculator) {
        this.expression = expression;
        this.stringCalculator = stringCalculator;
    }

    BigDecimal solve() {
        Stack<Integer> openBrackets = new Stack<>();
        for(int i=0; i<expression.length(); i++) {
            if(expression.charAt(i)=='(') {
                openBrackets.push(i);
            }
            if(expression.charAt(i)==')') {
                Integer openIndex = openBrackets.pop();
                BigDecimal decimal = solveDividedExpression(expression.substring(openIndex+1,i));

                for(Map.Entry<String,Function> functionEntry : stringCalculator.functionList) {
                    String key = functionEntry.getKey();
                    if(openIndex-key.length()>=0 && key.equalsIgnoreCase(expression.substring(openIndex-key.length(),openIndex))) {
                        decimal = functionEntry.getValue().evaluate(decimal);
                        openIndex -= key.length();
                    }
                }

                expression = expression.substring(0,openIndex) + decimal.toPlainString() + expression.substring(i+1);
                i = openIndex;


            }
        }

        String result =  solveDividedExpression(expression).setScale(stringCalculator.getPrecision(), RoundingMode.HALF_UP).toPlainString();
        String output = !result.contains(".") ? result : result.replaceAll("0*$", "").replaceAll("\\.$", "");
        return new BigDecimal(output);
    }



    private List<String> divideUsingOperators(String expression) {
        List<String> output = new ArrayList<>();
        Set<String> operators = stringCalculator.operatorList.getOperators();
        for(int i=1; i<expression.length(); i++)
            for (String operator : operators) {
                if (expression.length() - i < operator.length())
                    continue;
                if (expression.substring(i, i + operator.length()).equals(operator)) {
                    output.add(expression.substring(0, i));
                    output.add(operator);
                    expression = expression.substring(i + operator.length());
                    i = 0;
                    break;
                }

            }
        output.add(expression);
        return output;
    }


    private BigDecimal solveDividedExpression(String e) {
        List<String> expression = divideUsingOperators(e);
        for(Map.Entry<String, Operator> entry: stringCalculator.operatorList) {
            for(int i=1; i<expression.size(); i+=2) {
                if(entry.getKey().equals(expression.get(i))) {
                    String val1 = expression.get(i-1);
                    String val2 = expression.get(i+1);

                    BigDecimal val = stringCalculator.constants.getConstant(val1);
                    if(val != null)
                        val1 = val.toPlainString();
                    val = stringCalculator.constants.getConstant(val2);
                    if(val != null)
                        val2 = val.toPlainString();
                    BigDecimal value1 = new BigDecimal(val1);
                    BigDecimal value2 = new BigDecimal(val2);
                    expression.set(i-1,entry.getValue().evaluate(value1,value2).toPlainString());
                    expression.remove(i);
                    expression.remove(i);
                    i-=2;
                }
            }
        }

        BigDecimal result = stringCalculator.constants.getConstant(expression.get(0));

        return result != null? result: new BigDecimal(expression.get(0));
    }




}
