package com.arcgistest.activities.launcher

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.arcgistest.R
import com.arcgistest.activities.main.MainActivity

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(R.layout.activity_launcher)

        initData()
    }

    private fun initData() {
        actionOpenMainActivity()
    }

    private fun actionOpenMainActivity() {
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        val i = Intent(this, MainActivity::class.java)
        this.startActivity(i)
        this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        this.finish()
    }
}