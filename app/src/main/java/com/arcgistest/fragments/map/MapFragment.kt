@file:JvmName("MapFragment")

package com.arcgistest.fragments.map

import android.os.Bundle
import android.view.View
import com.arcgistest.R
import com.arcgistest.fragments.BaseFragment
import com.arcgistest.fragments.map.di.MapFragmentModule
import javax.inject.Inject
import com.arcgistest.fragments.map.di.DaggerMapFragmentComponent
import com.esri.arcgisruntime.mapping.ArcGISMap
import com.esri.arcgisruntime.mapping.Basemap
import kotlinx.android.synthetic.main.fragment_map.*

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
        //presenter.attachView(this)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val map = ArcGISMap(Basemap.Type.TOPOGRAPHIC, 34.056295, -117.195800, 16)
        mapView.map = map
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //presenter.detachView()
    }

    override fun onPause() {
        super.onPause()
        mapView.pause()
    }

    override fun onResume() {
        super.onResume()
        mapView.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.dispose()
    }
}