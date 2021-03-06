package com.arcgistest.activities.main

import com.arcgistest.activities.main.router.MainRouter

class MainPresenter(private val router: MainRouter) {

    var mView: MainView? = null

    fun bindView(view: MainView) {
        this.mView = view
    }

    fun unbindView() {
        this.mView = null
    }

    fun showMapView() {
        router.showMapView()
    }
}