package com.jintin.clerk.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jintin.clerk.app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, LogListFragment())
                .commit()
        }
    }
}
