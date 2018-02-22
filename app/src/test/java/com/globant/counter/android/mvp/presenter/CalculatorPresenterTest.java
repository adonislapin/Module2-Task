package com.globant.counter.android.mvp.presenter;

import com.globant.counter.android.mvp.model.MultiplicationModel;
import com.globant.counter.android.mvp.model.SumModel;
import com.globant.counter.android.mvp.view.CalculatorView;
import com.globant.counter.android.util.regex.RegexUtil;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class CalculatorPresenterTest {

    private CalculatorPresenter presenter;
    private SumModel model;
    private MultiplicationModel multiplicationModel;
    @Mock CalculatorView view;
    @Mock RegexUtil regexUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new SumModel();
        multiplicationModel = new MultiplicationModel();
        presenter = new CalculatorPresenter(view, model, multiplicationModel);
    }

    @Test
    public void onSolveOperationPressed() {
        String data = "4+3=";

        //Test the full operation process
        presenter.onSolveOperationPressed(data);
        assertNotNull(model.getResult());
        assertTrue( "The buffer is not clean",0D == model.getResult());

        //Test the internal process of the sum
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4D,3D});
        model.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",7.0D , model.getResult(), 0.001D );
        model.clean();
        assertTrue( "The buffer is not clean",0D == model.getResult());

        //Test the internal process of the sum with decimals
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4.5D,3.5D});
        model.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",8.0D , model.getResult(), 0.001D );
    }

    @Test
    public void shouldMultiplicateNumbers() {
        String data = "4*3=";

        //Test the full operation process
        presenter.onSolveOperationPressed(data);
        assertNotNull(multiplicationModel.getResult());
        assertTrue( "The buffer is not clean",0D == multiplicationModel.getResult());

        //Test the internal process of the sum
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4D,3D});
        multiplicationModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",12.0D , multiplicationModel.getResult(), 0.001D );
        multiplicationModel.clean();
        assertTrue( "The buffer is not clean",0D == multiplicationModel.getResult());

        //Test the internal process of the sum with decimals
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4.5D,3.5D});
        multiplicationModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",15.75D , multiplicationModel.getResult(), 0.001D );
        multiplicationModel.clean();
        assertTrue( "The buffer is not clean",0D == multiplicationModel.getResult());

        //Test the internal process of the sum
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{-4D,3D});
        multiplicationModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",-12.0D , multiplicationModel.getResult(), 0.001D );
        multiplicationModel.clean();
        assertTrue( "The buffer is not clean",0D == multiplicationModel.getResult());

    }

}