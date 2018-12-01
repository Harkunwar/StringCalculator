package com.harkunwar.StringCalculator.model;

import java.math.BigDecimal;
import java.util.*;

public class ExpressionHistory implements Iterable<ExpressionHistory.Entry> {

    private Map<String, BigDecimal> history;


    public ExpressionHistory() {
        this.history = new LinkedHashMap<>();
    }

    void put(String expression, BigDecimal result) {
        history.put(expression,result);
    }

    public int size() {
        return history.size();
    }

    @Override
    public Iterator<ExpressionHistory.Entry> iterator() {
        return new ExpressionHistoryIterator();
    }


    private class ExpressionHistoryIterator implements  Iterator<ExpressionHistory.Entry> {

        private int index = 1;
        private List<String> keyList = new ArrayList<>(history.keySet());

        @Override
        public boolean hasNext() {
            return index<size();
        }

        @Override
        public ExpressionHistory.Entry next() {
            String expression = keyList.get(size()-index++);
            BigDecimal result = history.get(expression);
            return new Entry(expression,result);
        }
    }

    public class Entry implements Map.Entry<String, BigDecimal> {

        private String key;
        private BigDecimal value;

        Entry(String key, BigDecimal value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public BigDecimal getValue() {
            return this.value;
        }

        @Override
        public BigDecimal setValue(BigDecimal value) {
            return this.value = value;
        }

    }
}
