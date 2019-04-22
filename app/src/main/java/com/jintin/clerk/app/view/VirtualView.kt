package com.jintin.clerk.app.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jintin.clerk.app.R
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.ui.LogListAdapter
import com.jintin.clerk.app.utils.PrefKey
import com.jintin.clerk.app.utils.getBool
import kotlinx.android.synthetic.main.service_list.view.*

/**
 * VirtualView for instant log
 */
class VirtualView : ConstraintLayout {

    /**
     * Listener for back event listener
     */
    interface OnBackListener {

        /**
         * On back click event
         */
        fun onBackClick()
    }

    private var backListener: OnBackListener? = null
    private val adapter = LogListAdapter()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.service_list, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun dispatchKeyEvent(event: KeyEvent?): Boolean {
        if (event?.keyCode == KeyEvent.KEYCODE_BACK) {
            backListener?.let {
                it.onBackClick()
                return true
            }
        }
        return super.dispatchKeyEvent(event)
    }

    /**
     * Set on Back key event listener
     */
    fun setOnBackListener(backListener: OnBackListener) {
        this.backListener = backListener
    }

    /**
     * Set logs
     */
    fun setData(list: List<ClerkLog>) {
        adapter.setData(list)
        if (list.isNotEmpty() && context.getBool(PrefKey.AUTO_SCROLL)) {
            recyclerView.scrollToPosition(list.size - 1)
        }
    }

}