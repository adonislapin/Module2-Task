package com.globant.counter.android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.globant.counter.android.mvp.model.CountModel;
<<<<<<< Updated upstream
import com.globant.counter.android.mvp.presenter.CountPresenter;
import com.globant.counter.android.mvp.view.CountView;
=======
import com.globant.counter.android.mvp.model.SubtractionModel;
import com.globant.counter.android.mvp.model.SumModel;
import com.globant.counter.android.mvp.presenter.CalculatorPresenter;
import com.globant.counter.android.mvp.view.CalculatorView;
>>>>>>> Stashed changes

public class MainActivity extends AppCompatActivity {

    private CountPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< Updated upstream
        presenter = new CountPresenter(new CountModel(), new CountView(this));
=======
        presenter = new CalculatorPresenter(new CalculatorView(this), new SumModel(), new SubtractionModel());
>>>>>>> Stashed changes
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
