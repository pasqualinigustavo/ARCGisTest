@file:JvmName("MapFragment")

package com.arcgistest.fragments.map

import android.os.Bundle
import android.util.Log
import android.view.View
import com.arcgistest.R
import com.arcgistest.fragments.BaseFragment
import com.arcgistest.fragments.map.di.DaggerMapFragmentComponent
import com.arcgistest.fragments.map.di.MapFragmentModule
import com.esri.android.map.ogc.kml.KmlLayer
import kotlinx.android.synthetic.main.fragment_map.*
import javax.inject.Inject


class MapFragment : BaseFragment(), MapFragmentView {

    companion object {
        val TAG = MapFragment::class.java.simpleName

        @JvmStatic
        fun newInstance() = MapFragment()
    }

    @Inject
    lateinit var presenter: MapPresenter

    override fun getLayoutId() = R.layout.fragment_map

    override fun injectComponents() {
        DaggerMapFragmentComponent.builder()
                .mainComponent(mainComponent)
                .mapFragmentModule(MapFragmentModule())
                .build()
                .inject(this)
    }

    override fun init() {
        Log.d(TAG, "init")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        Log.d(TAG, "onViewCreated")
        presenter.loadKmlFile()
    }

    override fun addLayer(kmlLayer: KmlLayer) {
        Log.d(TAG, "addLayer")
        mapView.addLayer(kmlLayer)
        mapView.enableWrapAround(true)
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun onResume() {
        super.onResume()
        mapView.unpause()
    }
}