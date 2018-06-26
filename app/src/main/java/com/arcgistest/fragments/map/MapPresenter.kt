package com.arcgistest.fragments.map

import android.os.Environment
import android.util.Log
import com.arcgistest.fragments.map.router.MapRouter
import com.esri.android.map.ogc.kml.KmlLayer
import kotlinx.android.synthetic.main.fragment_map.*
import java.io.File

class MapPresenter(private val interactor: MapInteractor,
                   private val router: MapRouter) {

    companion object {
        val TAG = MapPresenter::class.java.simpleName
    }

    private var view: MapFragmentView? = null

    fun attachView(view: MapFragmentView) {
        this.view = view
        Log.d(TAG, "attachView")
    }

    fun detachView() {
        this.view = null
    }

    fun loadKmlFile() {
        val file = interactor.loadKmlFile()
        if(file?.exists()) {
            val kmlLayer = KmlLayer(file.getAbsolutePath())
            view?.addLayer(kmlLayer)
        } else Log.d(TAG,"file null")
    }
}