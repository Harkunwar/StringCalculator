package com.harkunwar.StringCalculator;

import com.harkunwar.StringCalculator.model.StringCalculator;

public class Test {

    public static void main(String[] args)  {

        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.setExpression("2/3^PI");
        stringCalculator.setPrecision(5);
        System.out.println(stringCalculator.evaluate());

    }

}
