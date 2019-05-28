package com.jintin.clerk.app.view

import android.content.Context
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jintin.clerk.app.R
import com.jintin.clerk.app.obj.ClerkLog
import kotlinx.android.synthetic.main.layout_instant.view.*

/**
 * Floating layout for logs
 */
class InstantLayout : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        View.inflate(context, R.layout.layout_instant, this)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isMinimize()) {
                bubbleView.setMinimize(true)
                return true
            }
        }
        return super.dispatchKeyEvent(event)
    }

    /**
     * Set logs
     */
    fun setData(list: List<ClerkLog>) {
        virtualView.setData(list)
        bubbleView.setCount(list.size)
    }

    /**
     * Set bubble action listener
     */
    fun setBubbleActionListener(listener: BubbleView.OnBubbleActionListener) {
        bubbleView.setOnBubbleActionListener(object : BubbleView.OnBubbleActionListener {
            override fun onBubbleDragStart(x: Int, y: Int) {
                listener.onBubbleDragStart(x, y)
            }

            override fun onBubbleDragEnd() {
                listener.onBubbleDragEnd()
            }

            override fun onBubbleMinimize(minimize: Boolean) {
                virtualView.visibility = if (minimize) View.GONE else View.VISIBLE
                listener.onBubbleMinimize(minimize)
            }

            override fun onBubbleMove(x: Int, y: Int) {
                listener.onBubbleMove(x, y)
            }

        })
    }

    /**
     * Set alignment of count text
     */
    fun setCountAlignment(moveToStart: Boolean) {
        bubbleView.setCountAlignment(moveToStart)
    }

    /**
     * Check if minimize or not
     */
    fun isMinimize(): Boolean {
        return bubbleView.isMinimize()
    }

}