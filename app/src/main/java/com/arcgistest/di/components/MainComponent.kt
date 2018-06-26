package com.arcgistest.di.components

import com.arcgistest.activities.main.MainActivity
import com.arcgistest.activities.main.di.MainModule
import com.arcgistest.di.PerActivity

import dagger.BindsInstance
import dagger.Component

@PerActivity
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent {

    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        fun parent(parent: ApplicationComponent): Builder

        fun module(module: MainModule): Builder

        @BindsInstance
        fun target(target: MainActivity): Builder

        fun build(): MainComponent
    }
}
