package com.globant.counter.android.mvp.model;

public class MultiplicationModel extends BaseModel implements IOperation {

    @Override
    public void solveOperation(Object[] data) {
        for (Object d : data) {
            result *= (Double) d;
        }
    }

    @Override
    public void clean() {
        result = 0D;
    }
}
