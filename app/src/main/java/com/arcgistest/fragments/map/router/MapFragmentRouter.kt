package com.arcgistest.fragments.map.router

import com.arcgistest.main.activity.router.MapFragmentNavigator

class MapFragmentRouter(private val navigator: MapFragmentNavigator) : MapRouter {

    override fun showLocationFragment() {
        navigator.showMapView()
    }

}