package com.globant.counter.android.mvp.model;

public class SumModel extends BaseModel implements IOperation{

    @Override
    public void solveOperation(Object[] data) {
        for (Object d : data) {
            result += (Double)d;
        }
    }
}
