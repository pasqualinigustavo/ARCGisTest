package com.arcgistest.activities.main

import android.os.Bundle
import com.arcgistest.R
import com.arcgistest.activities.base.BaseActivity
import com.arcgistest.activities.main.di.MainModule
import com.arcgistest.di.components.DaggerMainComponent
import com.arcgistest.di.components.MainComponent
import javax.inject.Inject

class MainActivity : BaseActivity(), MainView {
    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    val component: MainComponent by lazy {
        DaggerMainComponent.builder()
                .parent(appComponent)
                .module(MainModule())
                .target(this)
                .build()
    }
    @Inject
    lateinit var presenter: MainPresenter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)
        presenter.bindView(this)
        presenter.showMapView();
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}