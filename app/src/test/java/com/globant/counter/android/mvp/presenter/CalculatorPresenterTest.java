package com.globant.counter.android.mvp.presenter;

import com.globant.counter.android.mvp.model.SubtractionModel;
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
    private SumModel sumModel;
    private SubtractionModel subtractionModel;
    @Mock CalculatorView view;
    @Mock RegexUtil regexUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sumModel = new SumModel();
        subtractionModel = new SubtractionModel();
        presenter = new CalculatorPresenter(view, sumModel, subtractionModel);
    }

    @Test
    public void shouldSumNumbersFromInputString() {
        String data = "4+3=";

        //Test the full operation process
        presenter.onSolveOperationPressed(data);
        assertNotNull(sumModel.getResult());
        assertTrue( "The buffer is not clean",0D == sumModel.getResult());

        //Test the internal process of the sum
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4D,3D});
        sumModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",7.0D , sumModel.getResult(), 0.001D );
        sumModel.clean();
        assertTrue( "The buffer is not clean",0D == sumModel.getResult());

        //Test the internal process of the sum with decimals
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4.5D,3.5D});
        sumModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",8.0D , sumModel.getResult(), 0.001D );
    }

    @Test
    public void shouldSubtractNumbersFromInputString() {
        String data = "4-3=";

        //Test the full operation process
        presenter.onSolveOperationPressed(data);
        assertNotNull(subtractionModel.getResult());
        assertTrue( "The buffer is not clean",0D == subtractionModel.getResult());

        //Test the internal process of the sum
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4D,3D});
        subtractionModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",1.0D , subtractionModel.getResult(), 0.001D );
        subtractionModel.clean();
        assertTrue( "The buffer is not clean",0D == subtractionModel.getResult());

        //Test the internal process of the sum
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{-4D,3D});
        subtractionModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",-7.0D , subtractionModel.getResult(), 0.001D );
        subtractionModel.clean();
        assertTrue( "The buffer is not clean",0D == subtractionModel.getResult());

        //Test the internal process of the sum with decimals
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4.5D,3.5D});
        subtractionModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",1.0D , subtractionModel.getResult(), 0.001D );
        subtractionModel.clean();
        assertTrue( "The buffer is not clean",0D == subtractionModel.getResult());

        //Test the internal process of the sum with decimals
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{10D,3.5D, 2D, 0.3D});
        subtractionModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",4.2D , subtractionModel.getResult(), 0.001D );
        subtractionModel.clean();
        assertTrue( "The buffer is not clean",0D == subtractionModel.getResult());
    }

}