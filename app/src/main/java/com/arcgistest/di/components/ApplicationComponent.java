package com.arcgistest.di.components;

import android.content.Context;

import com.arcgistest.di.modules.ApplicationDIModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationDIModule.class
})
public interface ApplicationComponent {

    Context context();

    void inject(ComponentHolder holder);
}
