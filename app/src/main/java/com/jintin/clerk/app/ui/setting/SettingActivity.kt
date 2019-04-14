package com.jintin.clerk.app.ui.setting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jintin.clerk.app.R

/**
 * SettingActivity
 */
class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, SettingFragment())
                .commit()
        }
    }
}