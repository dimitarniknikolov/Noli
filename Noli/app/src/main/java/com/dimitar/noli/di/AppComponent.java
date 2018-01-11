package com.dimitar.noli.di;

import android.app.Application;

import com.dimitar.noli.NoliApp;
import com.dimitar.noli.di.module.ActivitiesModule;
import com.dimitar.noli.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ActivitiesModule.class,
        ViewModelModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(NoliApp app);

}
