package com.arcgistest.di;

import com.arcgistest.app.ARCGIApplication;
import com.arcgistest.di.components.ApplicationComponent;
import com.arcgistest.di.components.DaggerApplicationComponent;
import com.arcgistest.di.modules.ApplicationDIModule;


public class ApplicationDependency {

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent(ARCGIApplication application) {
        return this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationDIModule(new ApplicationDIModule(application))
                .build();
    }

}


