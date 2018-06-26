package com.arcgistest.activities.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.arcgistest.R;
import com.arcgistest.activities.main.MainActivity;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_launcher);

        initData();
    }

    private void initData() {
        actionOpenMainActivity();
    }

    private void actionOpenMainActivity() {
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        this.finish();
    }
}