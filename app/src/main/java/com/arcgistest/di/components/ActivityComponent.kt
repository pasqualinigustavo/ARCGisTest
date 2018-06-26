package com.arcgistest.di.components

import android.app.Activity

import com.arcgistest.di.PerActivity
import com.arcgistest.di.modules.ActivityModule

import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun activity(): Activity
}
