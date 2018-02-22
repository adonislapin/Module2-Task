package com.globant.counter.android.mvp.presenter;

import android.app.Activity;

import com.globant.counter.android.R;
import com.globant.counter.android.mvp.model.SumModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.util.bus.observers.SolveOperationButtonObserver;
import com.globant.counter.android.util.regex.RegexUtil;

public class CalculatorPresenter {

    private CalculatorView view;
    private RegexUtil regexUtil;

    private SumModel sumModel;

    public CalculatorPresenter(CalculatorView view, SumModel sumModel) {
        this.view = view;
        this.sumModel = sumModel;
        this.regexUtil = new RegexUtil();
    }

    public void onSolveOperationPressed(String data){
        if(regexUtil.isSumValid(data)){
            sumModel.solveOperation(regexUtil.cleanData(data));
            view.setResult(data + sumModel.getResult());
            sumModel.clean();
        } else {
            view.setResult(view.getContext().getString(R.string.error_unknown_operation));
        }
    }

    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new SolveOperationButtonObserver() {
            @Override
            public void onEvent(SolveOperationButton value) {
                onSolveOperationPressed(value.getData());
            }
        });

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }


}
