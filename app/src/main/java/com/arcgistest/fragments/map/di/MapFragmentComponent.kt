package com.arcgistest.fragments.map.di

import com.arcgistest.di.components.MainComponent
import com.arcgistest.fragments.map.MapFragment
import com.soulplatform.lover.di.PerFragment
import dagger.Component

@PerFragment
@Component(modules = arrayOf(MapFragmentModule::class), dependencies = arrayOf(MainComponent::class))
interface MapFragmentComponent {

    fun inject(target: MapFragment)

}