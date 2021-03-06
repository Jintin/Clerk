package com.jintin.clerk.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jintin.clerk.app.R

/**
 * Base Activity for single fragment
 */
abstract class ContainerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, getFragment())
                .commit()
        }
    }

    /**
     * Return target Fragment
     */
    abstract fun getFragment(): Fragment

}