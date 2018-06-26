package com.arcgistest.activities.main

import com.arcgistest.activities.main.router.MainRouter

class MainPresenter(private val interactor: MainInteractor,
                    private val router: MainRouter) {

    var mainActivity : MainActivity? = null

    fun doOnStop() = interactor.doOnStop()

    fun bindView(mainActivity: MainActivity) {
        this.mainActivity = mainActivity
    }

    fun showMapView() {
        router.showMapView()
    }


}