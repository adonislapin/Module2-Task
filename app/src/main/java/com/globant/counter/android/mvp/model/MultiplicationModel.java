package com.globant.counter.android.mvp.model;

public class MultiplicationModel extends BaseModel implements IOperation {

    @Override
    public void solveOperation(Object[] data) {
        for (int i = 0 ; i < data.length ; i++) {
            double d = (double) data[i];
            if(i == 0){
                result = d;
            } else {
                result *= d;
            }
        }
    }

    @Override
    public void clean() {
        result = 0D;
    }
}
