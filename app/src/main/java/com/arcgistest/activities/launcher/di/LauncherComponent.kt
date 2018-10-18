package com.arcgistest.activities.launcher.di

import com.arcgistest.activities.launcher.LauncherActivity
import com.arcgistest.di.PerActivity
import com.arcgistest.di.components.ApplicationComponent
import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(LauncherModule::class))
interface LauncherComponent {

    fun inject(activity: LauncherActivity)

    @Component.Builder
    interface Builder {
        fun parent(parent: ApplicationComponent): Builder
        fun module(module: LauncherModule): Builder
        @BindsInstance
        fun target(target: LauncherActivity): Builder

        fun build(): LauncherComponent
    }
}
