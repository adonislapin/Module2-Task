package com.globant.counter.android.mvp.model;


public class BaseModel {
    Double result = 0D;

    public Double getResult() {
        return result;
    }

    public void clean() {
        result = 0D;
    }
}
