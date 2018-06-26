package com.arcgistest.di.components;

public class ComponentHolder {

    private static ComponentHolder instance = null;

    private ComponentHolder() {
    }

    public static ComponentHolder getInstance() {
        if (instance == null)
            instance = new ComponentHolder();
        return instance;
    }

}
