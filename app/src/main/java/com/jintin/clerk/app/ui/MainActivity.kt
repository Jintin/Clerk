package com.jintin.clerk.app.ui

import android.view.Menu
import android.view.MenuItem
import com.jintin.clerk.app.R
import com.jintin.clerk.app.ui.setting.SettingActivity
import com.jintin.clerk.app.utils.startActivity


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
            R.id.action_settings -> startActivity(SettingActivity::class)
        }
        return super.onOptionsItemSelected(item)
    }

}
