package com.arcgistest.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.graphics.drawable.BuildConfig;
import android.support.multidex.MultiDexApplication;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.arcgistest.di.ApplicationDependency;
import com.arcgistest.di.components.ApplicationComponent;
import com.arcgistest.di.components.ComponentHolder;

public class ARCGIApplication extends MultiDexApplication {

    private static Context context = null;
    public static String TAG = ARCGIApplication.class.getSimpleName();
    public static ApplicationComponent graph;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
        ComponentHolder holder = ComponentHolder.INSTANCE;
        graph.inject(holder);
        if (!BuildConfig.DEBUG) {
            Log.e(ARCGIApplication.class.getSimpleName(), "Crashlytics ON");
            //Fabric.with(this, new Crashlytics());
        } else {
            Log.e(ARCGIApplication.class.getSimpleName(), "Crashlytics OFF");
        }

        this.TAG = this.getClass().getSimpleName();

        //Global context of app
        ARCGIApplication.context = getApplicationContext();
    }

    private void initializeInjector() {
        graph = new ApplicationDependency().getApplicationComponent(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return graph;
    }

    public static Context getAppContext() {
        return ARCGIApplication.context;
    }

    public static boolean isAccessFineLocationPermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isAccessCoarseLocationPermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isNetworkStatePermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_NETWORK_STATE);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isInternetPermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.INTERNET);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isCameraPermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CAMERA);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isReadExternalStoragePermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isWriteExternalStoragePermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isReadContactsPermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CONTACTS);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isReadSmsPermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_SMS);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isReadCallsPermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CALL_LOG);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isCallPhonePermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.CALL_PHONE);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isReadPhoneStatePermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_PHONE_STATE);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isRecordAudioPermissionGranted(Context context) {
        int permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO);

        return permission == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

        switch (level) {
            case TRIM_MEMORY_COMPLETE:
                this.tryToCleanMemory();
                break;

            case TRIM_MEMORY_UI_HIDDEN:
                this.tryToCleanMemory();
                break;

            case TRIM_MEMORY_RUNNING_CRITICAL:
                this.cleanMemoryCache();
                break;
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d(TAG, "onLowMemory");
        this.cleanMemoryCache();
    }

    private void tryToCleanMemory() {
        this.cleanMemoryCache();
        System.gc();
    }

    private void cleanMemoryCache() {
        //TODO: Clean memory
    }
}
