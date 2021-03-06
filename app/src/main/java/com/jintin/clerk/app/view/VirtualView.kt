package com.jintin.clerk.app.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.jintin.clerk.app.R
import com.jintin.clerk.app.obj.ClerkLog
import com.jintin.clerk.app.ui.LogListAdapter
import com.jintin.clerk.app.utils.PrefKey
import com.jintin.clerk.app.utils.getBool
import kotlinx.android.synthetic.main.view_virtual.view.*

/**
 * VirtualView for instant log
 */
class VirtualView : ConstraintLayout {

    private val adapter = LogListAdapter()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.view_virtual, this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
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