package com.dimitar.noli.di.module;

import com.dimitar.noli.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created on 9/18/2017 for AgWeatherTools.
 */
@Module
public abstract class ActivitiesModule {

    @ContributesAndroidInjector()
    abstract MainActivity contributeMainActivity();

}
