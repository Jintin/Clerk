package com.jintin.clerk.app.ui

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import com.jintin.clerk.app.R
import com.jintin.clerk.app.ui.setting.SettingActivity

/**
 * MainActivity
 */
class MainActivity : ContainerActivity() {

    override fun getFragment() = LogListFragment()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.action_settings -> startActivity(Intent(this, SettingActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

}
