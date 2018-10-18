package com.arcgistest.activities.launcher.di

import com.arcgistest.activities.launcher.LauncherActivity
import com.arcgistest.activities.launcher.LauncherPresenter
import com.arcgistest.activities.launcher.router.LauncherActivityRouter
import com.arcgistest.activities.launcher.router.LauncherRouter
import com.arcgistest.activities.main.MainActivity
import com.arcgistest.activities.main.MainPresenter
import com.arcgistest.activities.main.router.MainRouter
import com.arcgistest.di.PerActivity
import com.arcgistest.main.activity.router.LauncherNavigator
import com.arcgistest.main.activity.router.MainNavigator
import dagger.Module
import dagger.Provides

@Module
class LauncherModule {

    @Provides
    @PerActivity
    fun router(navigator: LauncherNavigator): LauncherRouter = LauncherActivityRouter(navigator)

    @Provides
    @PerActivity
    fun navigator(activity: LauncherActivity) = LauncherNavigator(activity)

    @Provides
    @PerActivity
    fun presenter(router: LauncherRouter) =
            LauncherPresenter(router)
}