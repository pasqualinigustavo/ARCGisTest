package com.arcgistest.activities.main.router

import com.arcgistest.main.activity.router.MainNavigator

class MainActivityRouter(private val navigator: MainNavigator) : MainRouter {

    override fun showMapView() {
        navigator.showMapView()
    }

}