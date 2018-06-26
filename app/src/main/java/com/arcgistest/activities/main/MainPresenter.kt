package com.arcgistest.activities.main

import com.arcgistest.activities.main.router.MainRouter

class MainPresenter(private val interactor: MainInteractor,
                    private val router: MainRouter) {

    var mainActivity : MainActivity? = null

    fun bindView(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }

    fun unbindView() {
        this.mainActivity = null
    }

    fun showMapView() {
        router.showMapView()
    }


}