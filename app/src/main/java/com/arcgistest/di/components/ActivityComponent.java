package com.arcgistest.di.components;

import android.app.Activity;

import com.arcgistest.di.PerActivity;
import com.arcgistest.di.modules.ActivityModule;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
