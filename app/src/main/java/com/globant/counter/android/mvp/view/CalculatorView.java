package com.globant.counter.android.mvp.view;

import android.app.Activity;
import android.widget.EditText;
import android.widget.TextView;

import com.globant.counter.android.R;
import com.globant.counter.android.util.bus.observers.CountButtonUpBusObserver;
import com.globant.counter.android.util.bus.observers.ResetButtonObserver;
import com.globant.counter.android.util.bus.RxBus;
import com.globant.counter.android.util.bus.observers.SolveOperationButtonObserver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CalculatorView extends ActivityView {

    @BindView(R.id.edit_input_data) EditText inputData;

    public CalculatorView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void setResult(String result) {
        inputData.setText(result);
    }

    @OnClick(R.id.button_solve)
    public void countButtonPressed() {
        SolveOperationButtonObserver.SolveOperationButton operationButton = new SolveOperationButtonObserver.SolveOperationButton();
        operationButton.setData(inputData.getText().toString());
        RxBus.post(operationButton);
    }

}
