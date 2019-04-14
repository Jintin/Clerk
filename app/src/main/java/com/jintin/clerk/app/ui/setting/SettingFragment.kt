package com.jintin.clerk.app.ui.setting

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.jintin.clerk.app.R

/**
 * SettingFragment
 */
class SettingFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}