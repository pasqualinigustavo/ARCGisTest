package com.arcgistest.activities.base;

public interface BaseView {
    void showError(String errorText);

    void showError(String title, String errorText);

    void showLoading(String loadingMessage);

    void loadComplete();
}
