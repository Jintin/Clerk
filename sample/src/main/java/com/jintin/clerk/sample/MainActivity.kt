package com.jintin.clerk.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jintin.clerk.lib.ClerkUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MainActivity
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        send.setOnClickListener {
            ClerkUtils.log(this, channelText.text.toString(), logText.text.toString())
        }
    }
}
