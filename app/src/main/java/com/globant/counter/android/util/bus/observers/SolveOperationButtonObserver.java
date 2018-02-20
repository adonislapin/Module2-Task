package com.globant.counter.android.util.bus.observers;

public abstract class SolveOperationButtonObserver extends BusObserver<SolveOperationButtonObserver.SolveOperationButton> {

    public SolveOperationButtonObserver() {
        super(SolveOperationButton.class);
    }

    public static class SolveOperationButton{
        String data;

        public void setData(String data) {
            this.data = data;
        }

        public String getData() {
            return data;
        }
    }
}
