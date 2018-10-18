package com.arcgistest.fragments.map.di

import com.arcgistest.activities.main.MainActivity
import com.arcgistest.fragments.map.MapInteractor
import com.arcgistest.fragments.map.MapPresenter
import com.arcgistest.fragments.map.router.MapFragmentRouter
import com.arcgistest.fragments.map.router.MapRouter
import com.arcgistest.main.activity.router.MapFragmentNavigator
import com.soulplatform.lover.di.PerFragment
import dagger.Module
import dagger.Provides

@Module
class MapFragmentModule {

    @Provides
    @PerFragment
    fun interactor(): MapInteractor {
        return MapInteractor()
    }

    @Provides
    @PerFragment
    fun navigator() = MapFragmentNavigator()

    @Provides
    @PerFragment
    fun router(navigator: MapFragmentNavigator): MapRouter = MapFragmentRouter(navigator)

    @Provides
    @PerFragment
    fun presenter(router: MapRouter, interactor: MapInteractor) =
            MapPresenter(interactor, router)
}