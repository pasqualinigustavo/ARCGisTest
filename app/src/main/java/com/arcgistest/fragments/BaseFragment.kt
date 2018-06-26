package com.arcgistest.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arcgistest.activities.main.MainActivity
import com.arcgistest.app.ARCGIApplication
import com.arcgistest.di.HasComponent
import com.arcgistest.di.components.ApplicationComponent
import com.arcgistest.di.components.MainComponent

abstract class BaseFragment : Fragment() {

    private var hasContext = false
    private val layoutId = null

    val mainComponent: MainComponent?
        get() {
            var component: MainComponent? = null

            if (activity is MainActivity) {
                component = (activity as MainActivity).component
            }

            return component
        }

    abstract fun getLayoutId(): Int

    protected val appComponent: ApplicationComponent
        get() = (activity!!.application as ARCGIApplication).applicationComponent

    override fun onDetach() {
        super.onDetach()
        hasContext = false
    }

    abstract fun init()

    fun viewCreated() {}

    abstract fun injectComponents()

    override fun onCreate(savedInstanceState: Bundle?) {
        tryInjection(activity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        tryInjection(activity)
        val view = inflater.inflate(layoutId, container, false)
        init()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewCreated()
    }

    private fun tryInjection(context: Context?) {
        if (!hasContext && context != null) {
            hasContext = true
            injectComponents()
        }
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    protected fun <C> getComponent(componentType: Class<C>): C {
        return componentType.cast((activity as HasComponent<C>).component)
    }

    companion object {
        private val TAG = BaseFragment::class.java.canonicalName
    }
}
