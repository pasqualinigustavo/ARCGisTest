package com.arcgistest.main.activity.router

import android.support.v4.app.Fragment
import android.util.Log
import com.arcgistest.R
import com.arcgistest.activities.main.MainActivity
import com.arcgistest.fragments.map.MapFragment

class MainNavigator(private val activity: MainActivity) {

    companion object {
        private val TAG = MainNavigator::class.java.simpleName
    }

    fun showMapView() {
        val fragment = MapFragment.newInstance()
        val tag = fragment::class.java.simpleName
        setFragment(fragment, tag)
    }

    private fun setFragment(fragment: Fragment, addToBackStack: Boolean = false) {
        val transaction = activity.supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_content, fragment)
        if (addToBackStack) transaction.addToBackStack(null)
        try {
            transaction.commitAllowingStateLoss()
            activity.supportFragmentManager.executePendingTransactions()
        } catch (e: Exception) {

        }
    }

    private fun setFragment(fragment: Fragment, tag: String) {
        try {
            activity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_content, fragment)
                    .addToBackStack(tag)
                    .commit()
            activity.supportFragmentManager.executePendingTransactions()
        } catch (e: Exception) {
            Log.e(TAG, e.message)
        }
    }

    public fun clearBackStack() {
        val fm = activity.supportFragmentManager
        val count = fm.backStackEntryCount
        (0 until count).forEach { _ -> fm.popBackStack() }
    }
}