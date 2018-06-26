package com.arcgistest.di.modules

import android.content.Context

import com.arcgistest.app.ARCGIApplication

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class ApplicationDIModule(private val application: ARCGIApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return application
    }
}