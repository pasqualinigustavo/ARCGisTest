package com.arcgistest.fragments.map

import android.os.Environment
import android.util.Log
import java.io.File

class MapInteractor() {

    companion object {
        val TAG = MapInteractor::class.java.simpleName
    }

    fun loadKmlFile(): File {
        Log.d(TAG,"loadKmlFile")
        val sdcard = Environment.getExternalStorageDirectory()
        return File(sdcard.absolutePath + File.separator + "file_other.kml")
    }
}