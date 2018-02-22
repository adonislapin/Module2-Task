package com.globant.counter.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.globant.counter.android.mvp.model.CountModel;
import com.globant.counter.android.mvp.model.SumModel;
import com.globant.counter.android.mvp.presenter.CalculatorPresenter;
import com.globant.counter.android.mvp.view.CalculatorView;

public class MainActivity extends AppCompatActivity {

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new CalculatorPresenter(new CalculatorView(this), new SumModel());
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }
}
