@file:JvmName("PartyBeginFragment")

package com.arcgistest.fragments.map

import com.arcgistest.R
import com.arcgistest.fragments.BaseFragment
import com.arcgistest.fragments.map.di.MapFragmentModule
import javax.inject.Inject
import com.arcgistest.fragments.map.di.DaggerMapFragmentComponent

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

    override fun viewCreated() {
        super.viewCreated()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        //presenter.detachView()
    }
}