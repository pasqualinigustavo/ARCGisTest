package com.arcgistest.main.activity.router

import android.content.Intent
import com.arcgistest.activities.launcher.LauncherActivity
import com.arcgistest.activities.main.MainActivity

class LauncherNavigator(private val activity: LauncherActivity) {

    companion object {
        private val TAG = LauncherNavigator::class.java.simpleName
    }

    fun showMainView() {
        val i = Intent(activity, MainActivity::class.java)
        activity.startActivity(i)
        activity.finish()
    }

}