package com.globant.counter.android.util.regex;

import android.util.Log;

import java.util.ArrayList;

public class RegexUtil {

    private static final String SUM_REGEX = "^(?!.*[\\+\\.]{2}.*)\\d[\\d\\.\\+]*\\d\\=$";
    private static final String SUBTRACTION_REGEX = "^(?!.*[\\-\\.]{2}.*)\\d[\\d\\.\\-]*\\d\\=$";
    private static final String MULTIPLICATION_REGEX = "^(?!.*[\\*\\.]{2}.*)\\d[\\d\\.\\*]*\\d\\=$";
    private static final String DIVISION_REGEX = "^-*[0-9,\\.]+\\/-*[0-9,\\.]+\\=$";
    private static final String MODULE_REGEX = "^-*[0-9]+\\%-*[0-9]+\\=$";
    private static final String MODULE_ZERO_REGEX = ".*\\%0([^.]|$|\\.(0{4,}.*|0{1,4}([^0-9]|$))).*";
    private static final String SPLIT_REGEX = "[-+*/=%]";
    private static final String VALID_NUMBER_REGEX = "^[0-9]+([,.][0-9]+)?$";

    public boolean isSumValid(String data){
        return data.matches(SUM_REGEX);
    }

    public boolean isSubtractionValid(String data){
        return data.matches(SUBTRACTION_REGEX);
    }

    public boolean isMultiplicationValid(String data){
        return data.matches(MULTIPLICATION_REGEX);
    }

    public boolean isDivisionValid(String data){
        return data.matches(DIVISION_REGEX);
    }

    public boolean isModuleValid(String data){
        return data.matches(MODULE_REGEX) && !data.matches(MODULE_ZERO_REGEX);
    }

    public Object[] cleanData(String data){
        String [] temp = data.split(SPLIT_REGEX);

        ArrayList<Double> newData = new ArrayList<>();
        for(String string : temp){
            if(string.matches(VALID_NUMBER_REGEX)){
                newData.add(Double.valueOf(string+"D"));
            }
        }

        return newData.toArray();
    }
}
