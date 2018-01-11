package com.dimitar.noli.di.module;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.dimitar.noli.di.ViewModelKey;
import com.dimitar.noli.ui.list.MainViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Created on 9/18/2017 for AgWeatherTools.
 * Add ViewModels like:
 *
 * @Binds
 * @IntoMap
 * @ViewModelKey(ViewModel.class) abstract ViewModel bindViewModel(ViewModel viewModel);
 */

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);


    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(NoliViewModelFactory factory);

}
