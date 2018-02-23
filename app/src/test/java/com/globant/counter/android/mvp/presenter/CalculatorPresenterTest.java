package com.globant.counter.android.mvp.presenter;

import com.globant.counter.android.mvp.model.DivisionModel;
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
    private DivisionModel divisionModel;
    @Mock CalculatorView view;
    @Mock RegexUtil regexUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        model = new SumModel();
        divisionModel = new DivisionModel();
        presenter = new CalculatorPresenter(view, model, divisionModel);
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
    public void shouldDivideNumbers() {
        String data = "4/3=";

        //Test the full operation process
        presenter.onSolveOperationPressed(data);
        assertNotNull(divisionModel.getResult());
        assertTrue( "The buffer is not clean",0D == divisionModel.getResult());

        //Test the internal process of the sum
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{4D,3D});
        divisionModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",1.33D , divisionModel.getResult(), 0.1D );
        divisionModel.clean();
        assertTrue( "The buffer is not clean",0D == divisionModel.getResult());

        //Test the internal process of the sum with decimals
        when(regexUtil.cleanData(data)).thenReturn( new Object[]{10D,2D});
        divisionModel.solveOperation(regexUtil.cleanData(data));
        assertEquals("The result is incorrect",5.0D , divisionModel.getResult(), 0.001D );
        divisionModel.clean();
        assertTrue( "The buffer is not clean",0D == divisionModel.getResult());

        //Test to prevent the divide by zero operations
        RegexUtil regexUtilInternal = new RegexUtil();
        String zero = "4/0=";
        assertFalse("Should be false",  regexUtilInternal.isDivisionValid(zero) );

    }

}