package com.arcgistest.fragments.map

import com.esri.android.map.ogc.kml.KmlLayer

interface MapFragmentView {

    fun addLayer(kmlLayer: KmlLayer)

}