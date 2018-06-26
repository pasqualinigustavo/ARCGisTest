package com.arcgistest.di.components;

import com.arcgistest.activities.main.MainActivity;
import com.arcgistest.activities.main.di.MainModule;
import com.arcgistest.di.PerActivity;

import dagger.BindsInstance;
import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {MainModule.class})
public interface MainComponent {

    void inject(MainActivity activity_);

    @Component.Builder
    interface Builder {
        Builder parent(ApplicationComponent parent);

        Builder module(MainModule module);

        @BindsInstance
        Builder target(MainActivity target);

        MainComponent build();
    }
}
