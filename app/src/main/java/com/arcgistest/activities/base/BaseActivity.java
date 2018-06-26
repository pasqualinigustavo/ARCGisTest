package com.arcgistest.activities.base;


import android.support.v7.app.AppCompatActivity;

import com.arcgistest.app.ARCGIApplication;
import com.arcgistest.di.components.ApplicationComponent;
import com.arcgistest.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity {

    protected ApplicationComponent getAppComponent() {
        return ((ARCGIApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }
}