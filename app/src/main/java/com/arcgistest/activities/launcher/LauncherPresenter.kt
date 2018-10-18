package com.arcgistest.activities.launcher

import com.arcgistest.activities.launcher.router.LauncherRouter

class LauncherPresenter(private val router: LauncherRouter) {

    var mView: LauncherView? = null

    fun bindView(view: LauncherView) {
        this.mView = view
    }

    fun unbindView() {
        this.mView = null
    }

    fun showMainView() {
        router.showMainView()
    }
}