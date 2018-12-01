package com.harkunwar.StringCalculator.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Constants implements Iterable<Map.Entry<String, BigDecimal>> {

    private Map<String, BigDecimal> constants;

    public Constants() {
        constants = new HashMap<>();
        initializeDefaultConstants();
    }

    public void put(String constant, BigDecimal value) {
        constants.put(constant,value);
    }

    public void put(String constant, String value) {
        this.put(constant,new BigDecimal(value));
    }

    public void put(String constant, double value) {
        this.put(constant,new BigDecimal(value));
    }

    public BigDecimal getConstant(String key) {
        return constants.get(key);
    }

    public BigDecimal removeConstant(String key) {
        return constants.remove(key);
    }

    public Set<String> getConstants() {
        return constants.keySet();
    }

    public void clear() {
        constants = new HashMap<>();
    }

    void initializeDefaultConstants() {
        constants.put("PI",
                new BigDecimal("3.1415926535897932384626433832795028841971693993751058209749445923078164062862089986280348253421170679"));
        constants.put("e",
                new BigDecimal("2.71828182845904523536028747135266249775724709369995957496696762772407663"));
    }

    @Override
    public Iterator<Map.Entry<String, BigDecimal>> iterator() {
        return constants.entrySet().iterator();
    }
}
