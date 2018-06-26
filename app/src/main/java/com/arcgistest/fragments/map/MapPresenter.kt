package com.arcgistest.fragments.map

import com.arcgistest.fragments.map.router.MapRouter

class MapPresenter(private val interactor: MapInteractor,
                   private val router: MapRouter) {


    fun doOnStop() = interactor.doOnStop()
}