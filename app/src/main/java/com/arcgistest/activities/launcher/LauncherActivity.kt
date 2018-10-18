package com.arcgistest.activities.launcher

import android.os.Bundle
import com.arcgistest.R
import com.arcgistest.activities.base.BaseActivity
import com.arcgistest.activities.launcher.di.DaggerLauncherComponent
import com.arcgistest.activities.launcher.di.LauncherComponent
import com.arcgistest.activities.launcher.di.LauncherModule
import javax.inject.Inject

class LauncherActivity : BaseActivity(), LauncherView {

    companion object {
        val TAG: String = LauncherActivity::class.java.simpleName
    }

    val component: LauncherComponent by lazy {
        DaggerLauncherComponent.builder()
                .parent(appComponent)
                .module(LauncherModule())
                .target(this)
                .build()
    }

    @Inject
    lateinit var presenter: LauncherPresenter

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        component.inject(this)
        presenter.bindView(this)
        presenter.showMainView();
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}