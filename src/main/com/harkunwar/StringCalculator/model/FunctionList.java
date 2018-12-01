package com.harkunwar.StringCalculator.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class FunctionList implements Iterable<Map.Entry<String,Function>>{

    Map<String, Function> functions;

    FunctionList() {
        functions = new HashMap<>();
        initalizeDefaultFunctions();
    }

    private void initalizeDefaultFunctions() {

        Function SIN = (x) -> new BigDecimal(Math.sin(x.doubleValue()));
        functions.put("sin",SIN);

        Function COS = (x) -> new BigDecimal(Math.cos(x.doubleValue()));
        functions.put("cos",COS);

        Function TAN = (x) -> new BigDecimal(Math.tan(x.doubleValue()));
        functions.put("tan",TAN);

        Function LOG = (x) -> new BigDecimal(Math.log(x.doubleValue()));
        functions.put("log",LOG);

        Function LOG10 = (x) -> new BigDecimal(Math.log10(x.doubleValue()));
        functions.put("log10",LOG10);

        Function DEG = (x) -> new BigDecimal(Math.toDegrees(x.doubleValue()));
        functions.put("deg",DEG);

        Function RAD = (x) -> new BigDecimal(Math.toRadians(x.doubleValue()));
        functions.put("rad",RAD);

    }

    void add(String functionName, Function function) {
        functions.put(functionName, function);
    }

    Set<String> getFunctions() {
        return functions.keySet();
    }

    @Override
    public Iterator<Map.Entry<String, Function>> iterator() {
        return functions.entrySet().iterator();
    }
}
