package com.arcgistest.di.components

import android.content.Context

import com.arcgistest.di.modules.ApplicationDIModule

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(ApplicationDIModule::class))
interface ApplicationComponent {

    fun context(): Context

    fun inject(holder: ComponentHolder)
}
