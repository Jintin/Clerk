package com.jintin.clerk.app.ui.setting

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.jintin.clerk.app.R
import com.jintin.clerk.app.utils.PrefKey
import com.jintin.clerk.app.utils.getBool
import com.jintin.clerk.app.utils.hasOverlayPermission

/**
 * SettingFragment
 */
class SettingFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {
        private const val OVERLAY_PERMISSION = 222
    }

    private var overlayPreference: SwitchPreferenceCompat? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
        overlayPreference = findPreference(PrefKey.DRAW_OVERLAY)
    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences
            .registerOnSharedPreferenceChangeListener(this)

        overlayPreference?.let {
            if (it.isChecked && !hasOverlayPermission()) {
                turnOffDrawOverlay()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        if (key == PrefKey.DRAW_OVERLAY && getBool(PrefKey.DRAW_OVERLAY)) {
            requestOverlayPermission()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            OVERLAY_PERMISSION -> {
                if (!hasOverlayPermission()) {
                    turnOffDrawOverlay()
                }
            }
        }
    }

    private fun requestOverlayPermission() {
        if (!hasOverlayPermission()) {
            context?.let { context ->
                showPermissionDialog(context)
            }
        }
    }

    private fun showPermissionDialog(context: Context) {
        AlertDialog.Builder(context)
            .setTitle(R.string.request_overlay_title)
            .setMessage(R.string.request_overlay_message)
            .setOnCancelListener { turnOffDrawOverlay() }
            .setNegativeButton(android.R.string.cancel) { _, _ -> turnOffDrawOverlay() }
            .setPositiveButton(android.R.string.ok) { _, _ -> launchOverlayPage(context) }
            .show()
    }

    @TargetApi(Build.VERSION_CODES.M)
    private fun launchOverlayPage(context: Context) {
        val intent = Intent(
            Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
            Uri.parse("package:${context.packageName}")
        )
        startActivityForResult(intent, OVERLAY_PERMISSION)
    }

    private fun turnOffDrawOverlay() {
        overlayPreference?.isChecked = false
    }

}