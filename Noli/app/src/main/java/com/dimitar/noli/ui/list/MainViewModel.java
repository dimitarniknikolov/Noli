package com.dimitar.noli.ui.list;

import android.arch.lifecycle.ViewModel;
import android.util.Log;

import javax.inject.Inject;

/**
 * Created by dimitar on 10.01.18.
 */

public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";


    @Inject
    public MainViewModel() {

    }

    public void print() {

        Log.d(TAG, "print: alabala");
    }
}
