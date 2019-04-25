package com.jintin.clerk.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jintin.clerk.lib.ClerkUtils
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MainActivity
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ClerkUtils.init(application)
        val model = ViewModelProviders.of(this).get(MainViewModel::class.java)
        model.getTickState().observe(this, Observer { on ->
            tick.setText(if (on) R.string.stop_tick else R.string.start_tick)
        })

        model.getTickEvent().observeForever {
            ClerkUtils.log(channelText.text.toString(), logText.text.toString() + it)
//            // This one also work
//            ClerkUtils.log(this, channelText.text.toString(), logText.text.toString() + it)
        }

        tick.setOnClickListener {
            model.toggleTick()
        }

        send.setOnClickListener {
            ClerkUtils.log(channelText.text.toString(), logText.text.toString())
//            // This one also work
//            ClerkUtils.log(this, channelText.text.toString(), logText.text.toString())
        }
    }
}
