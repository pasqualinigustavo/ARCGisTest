package com.arcgistest.activities.base;


import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.arcgistest.app.ARCGIApplication;
import com.arcgistest.di.components.ApplicationComponent;
import com.arcgistest.di.modules.ActivityModule;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private ProgressDialog loading;
    private AlertDialog error;

    protected ApplicationComponent getAppComponent() {
        return ((ARCGIApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    @Override
    public void showError(String title, String errorText) {
        if (error == null)
            error = new AlertDialog.Builder(this)
                    .setTitle(title)
                    .create();

        error.setMessage(errorText);
        error.setOnDismissListener(dialog -> error = null);
        error.show();
    }

    @Override
    public void showError(String errorText) {
        if (error == null)
            error = new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .create();

        error.setMessage(errorText);
        error.show();
    }

    @Override
    public void showLoading(String loadingMessage) {
        if (loading == null) {
            loading = ProgressDialog.show(this, "Loading", loadingMessage, true, false);
            return;
        }

        if (!loading.isShowing()) {
            loading.setMessage(loadingMessage);
            loading.show();
        }
    }

    @Override
    public void loadComplete() {
        if (loading != null && loading.isShowing())
            loading.dismiss();
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}