package com.arcgistest.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arcgistest.activities.main.MainActivity;
import com.arcgistest.app.ARCGIApplication;
import com.arcgistest.di.HasComponent;
import com.arcgistest.di.components.ApplicationComponent;
import com.arcgistest.di.components.MainComponent;

import org.jetbrains.annotations.Nullable;

public abstract class BaseFragment extends Fragment {

    private boolean hasContext = false;
    private static final String TAG = BaseFragment.class.getCanonicalName();

    @Override
    public final void onDetach() {
        super.onDetach();
        hasContext = false;
    }

    public abstract int getLayoutId();

    public abstract void init();

    public void viewCreated() {
    }

    public abstract void injectComponents();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        tryInjection(getActivity());
        super.onCreate(savedInstanceState);
    }

    public MainComponent getMainComponent() {
        MainComponent component = null;

        if (getActivity() instanceof MainActivity) {
            component = ((MainActivity) getActivity()).getComponent();
        }

        return component;
    }

    @Nullable
    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tryInjection(getActivity());

        View view = inflater.inflate(getLayoutId(), container, false);
        init();
        return view;
    }

    @Override
    public void onViewCreated(View view, @android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewCreated();
    }

    private void tryInjection(Context context) {
        if (!hasContext && context != null) {
            hasContext = true;
            injectComponents();
        }
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

    protected ApplicationComponent getAppComponent() {
        return ((ARCGIApplication) getActivity().getApplication()).getApplicationComponent();
    }
}
