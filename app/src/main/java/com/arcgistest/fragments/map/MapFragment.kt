@file:JvmName("MapFragment")

package com.arcgistest.fragments.map

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import com.arcgistest.R
import com.arcgistest.fragments.BaseFragment
import com.arcgistest.fragments.map.di.DaggerMapFragmentComponent
import com.arcgistest.fragments.map.di.MapFragmentModule
import com.esri.android.map.ogc.kml.KmlLayer
import kotlinx.android.synthetic.main.fragment_map.*
import org.xmlpull.v1.XmlPullParserException
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
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

    private fun readFile1() {
        Log.d(TAG, "readFile1")
        val kmlFile = File("file:///sdcard/file_other.kml")
        if (kmlFile.exists()) {
            val kmlLayer = KmlLayer(kmlFile.getAbsolutePath())
            mapView.addLayer(kmlLayer)
        }
    }

    private fun readFile() {
        Log.d(TAG, "readFile")
        val kmlFile = File("file:///sdcard/file_other")
        if (kmlFile.exists()) {
            val kmlLayer = KmlLayer(kmlFile.getAbsolutePath())
            mapView.addLayer(kmlLayer)
        }
    }

    private fun createFileFromInputStream(inputStream: InputStream): File? {

        try {
            val f = File("temp.kml")
            val outputStream = FileOutputStream(f)
            val buffer = ByteArray(1024)
            var length = 0

            do {
                length = inputStream.read(buffer)
                outputStream.write(buffer, 0, length)
            } while (length > 0)

            outputStream.close()
            inputStream.close()

            return f
        } catch (e: IOException) {
            //Logging exception
            e.printStackTrace();
            Log.e(TAG, e.message);
        }

        return null
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