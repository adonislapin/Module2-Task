package com.globant.counter.android.mvp.presenter;

import com.globant.counter.android.mvp.model.SumModel;
import com.globant.counter.android.mvp.view.CalculatorView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PresenterTest {

    private CalculatorPresenter presenter;
    @Mock SumModel model;
    @Mock CalculatorView view;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        presenter = new CalculatorPresenter(view, model);
    }

    @Test
    public void shouldIncCountByOne() {
        /*
        when(model. getCount()).thenReturn(1);
        presenter.onCountButtonPressed();
        verify(model).inc();
        verify(view).setCount("1");
        verifyNoMoreInteractions(view); */
    }

    @Test
    public void shouldResetCount() {
        /*
        when(model.getCount()).thenReturn(3);
        presenter.onCountButtonPressed();
        presenter.onCountButtonPressed();
        presenter.onCountButtonPressed();
        verify(model,times(3)).inc();
        assertEquals(model.getCount(), 3);
        when(model.getCount()).thenReturn(0);
        presenter.onResetButtonPressed();
        verify(model).reset();
        assertEquals(model.getCount(), 0);
        verify(view, times(4)).setCount(anyString());
        verifyNoMoreInteractions(view); */
    }
}