package com.arcgistest.activities.launcher.router

import com.arcgistest.main.activity.router.LauncherNavigator

class LauncherActivityRouter(private val navigator: LauncherNavigator) : LauncherRouter {

    override fun showMainView() {
        navigator.showMainView()
    }
}