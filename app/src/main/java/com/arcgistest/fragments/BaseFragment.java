package com.arcgistest.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arcgistest.activities.base.BaseView;
import com.arcgistest.activities.main.MainActivity;
import com.arcgistest.app.ARCGIApplication;
import com.arcgistest.di.HasComponent;
import com.arcgistest.di.components.ApplicationComponent;
import com.arcgistest.di.components.MainComponent;

import org.jetbrains.annotations.Nullable;

public abstract class BaseFragment extends Fragment implements BaseView {

    private ProgressDialog loading;
    private AlertDialog error;
    private android.app.AlertDialog errorDialog;
    private boolean hasContext = false;
    private static final String TAG = "BaseFragment";

    @Override
    public final void onDetach() {
        super.onDetach();
        hasContext = false;
    }

    public abstract int getLayoutId();

    /**
     * Use this method to init fragment. This method is called right after view inflation.
     * Please, note, you don't need to Butterknife.bind();
     */
    public abstract void init();

    /**
     * Override this method to init listeners if you use KotlinExtensions.
     * at this point widgets were binded
     */
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

    private void inject(BaseFragment instance) {
        System.out.println("Instance: " + instance);
        //getAppComponent().inject(instance);
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

    @Override
    public void showError(String title, String errorText) {
        if (getActivity() == null) {
            return;
        }
        if (error == null)
            if (getActivity() != null) {
                error = new AlertDialog.Builder(getActivity())
                        .setTitle(title)
                        .create();
                error.setMessage(errorText);
                error.setOnDismissListener(dialog -> error = null);
                error.show();
            }
    }

    @Override
    public void showError(String errorText) {
        if (getActivity() == null) {
            return;
        }
        if (error == null)
            if (getActivity() != null) {
                error = new AlertDialog.Builder(getActivity())
                        .setTitle("Error")
                        .create();
                error.setMessage(errorText);
                error.setOnDismissListener(dialog -> error = null);
                error.show();
            }
    }

    @Override
    public void showLoading(String loadingMessage) {
        if (loading == null) {
            loading = ProgressDialog.show(getActivity(), "Loading", loadingMessage, true, false);
            return;
        }

        if (!loading.isShowing()) {
            loading.setMessage(loadingMessage);
            loading.show();
        }
    }

    @Override
    public void loadComplete() {
        if (loading != null && loading.isShowing()) loading.dismiss();
    }
}
