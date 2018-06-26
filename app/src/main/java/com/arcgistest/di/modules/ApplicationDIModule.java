package com.arcgistest.di.modules;

import android.content.Context;

import com.arcgistest.app.ARCGIApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationDIModule {
    private final ARCGIApplication application;

    public ApplicationDIModule(ARCGIApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return application;
    }
}