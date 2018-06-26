package com.arcgistest.activities.main.di

import com.arcgistest.activities.main.MainActivity
import com.arcgistest.activities.main.MainPresenter
import com.arcgistest.activities.main.router.MainActivityRouter
import com.arcgistest.activities.main.router.MainRouter
import com.arcgistest.di.PerActivity
import com.arcgistest.main.activity.router.MainNavigator
import dagger.Module
import dagger.Provides

@Module
class MainModule {

    @Provides
    @PerActivity
    fun router(navigator: MainNavigator): MainRouter = MainActivityRouter(navigator)

    @Provides
    @PerActivity
    fun navigator(activity: MainActivity) = MainNavigator(activity)

    @Provides
    @PerActivity
    fun presenter(router: MainRouter) =
            MainPresenter(router)
}