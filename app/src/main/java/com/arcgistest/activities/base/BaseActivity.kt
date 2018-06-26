package com.arcgistest.activities.base

import android.support.v7.app.AppCompatActivity
import com.arcgistest.app.ARCGIApplication
import com.arcgistest.di.components.ApplicationComponent
import com.arcgistest.di.modules.ActivityModule

abstract class BaseActivity : AppCompatActivity() {

    protected val appComponent: ApplicationComponent
        get() = (application as ARCGIApplication).applicationComponent

    protected val activityModule: ActivityModule
        get() = ActivityModule(this)
}